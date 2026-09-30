package de.shi.demo.seminare;

import com.jayway.jsonpath.JsonPath;
import de.shi.demo.seminare.registration.Registration;
import de.shi.demo.seminare.registration.RegistrationRepository;
import de.shi.demo.seminare.registration.RegistrationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static com.atlassian.oai.validator.mockmvc.OpenApiValidationMatchers.openApi;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Testet die API über HTTP. Jede Antwort wird zusätzlich gegen den Vertrag in api/openapi.yaml geprüft –
 * weicht das Backend vom Vertrag ab, schlägt der Test fehl.
 */
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class SeminareApiTest {

    private static final String CONTRACT = "../api/openapi.yaml";

    // Aus data.sql: Kurs 1 hat 12 freie Plätze, Kurs 2 ist mit 2 von 2 Plätzen ausgebucht.
    private static final long OPEN_COURSE = 1;
    private static final long FULL_COURSE = 2;
    // Aus data.sql: Anna hat als Erste einen festen Platz in Kurs 2.
    private static final long ANNA = 1;

    @Autowired
    MockMvc mvc;

    @Autowired
    RegistrationRepository registrations;

    @Test
    void listsCoursesWithFreePlaces() throws Exception {
        mvc.perform(get("/api/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == 1)].freePlaces").value(12))
                .andExpect(jsonPath("$[?(@.id == 2)].freePlaces").value(0))
                .andExpect(openApi().isValid(CONTRACT));
    }

    @Test
    void registersForCourseWithFreePlaces() throws Exception {
        register(OPEN_COURSE, "Clara Test", "clara@example.org")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.courseId").value(OPEN_COURSE))
                .andExpect(jsonPath("$.status").value("CONFIRMED"))
                .andExpect(openApi().isValid(CONTRACT));

        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 1)].freePlaces").value(11));
    }

    @Test
    void putsRegistrationOnWaitlistWhenCourseIsFull() throws Exception {
        register(FULL_COURSE, "Clara Test", "clara@example.org")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("WAITLISTED"))
                .andExpect(jsonPath("$.waitlistPosition").value(1))
                .andExpect(openApi().isValid(CONTRACT));

        register(FULL_COURSE, "David Test", "david@example.org")
                .andExpect(jsonPath("$.waitlistPosition").value(2));

        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 2)].freePlaces").value(0));
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED) // wie in Produktion: jede Anfrage in eigener Transaktion
    void reportsWaitlistPositionOutsideASharedTransaction() throws Exception {
        ResultActions result = register(FULL_COURSE, "Clara Test", "clara@example.org");
        try {
            result.andExpect(jsonPath("$.waitlistPosition").value(1));
        } finally {
            registrations.deleteById(idOf(result)); // Ausgangszustand für die anderen Tests
        }
    }

    @Test
    void cancellingAConfirmedRegistrationPromotesTheOldestWaitlistEntry() throws Exception {
        long clara = idOf(register(FULL_COURSE, "Clara Test", "clara@example.org"));
        long david = idOf(register(FULL_COURSE, "David Test", "david@example.org"));

        mvc.perform(delete("/api/registrations/{id}", ANNA)).andExpect(status().isNoContent());

        assertThat(registrations.findById(clara)).get()
                .extracting(Registration::getStatus).isEqualTo(RegistrationStatus.CONFIRMED);
        assertThat(registrations.findById(david)).get()
                .extracting(Registration::getStatus).isEqualTo(RegistrationStatus.WAITLISTED);
        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 2)].freePlaces").value(0));
    }

    @Test
    void cancellingAWaitlistEntryPromotesNobody() throws Exception {
        long clara = idOf(register(FULL_COURSE, "Clara Test", "clara@example.org"));
        long david = idOf(register(FULL_COURSE, "David Test", "david@example.org"));

        mvc.perform(delete("/api/registrations/{id}", clara)).andExpect(status().isNoContent());

        assertThat(registrations.findById(david)).get()
                .extracting(Registration::getStatus).isEqualTo(RegistrationStatus.WAITLISTED);
    }

    @Test
    void rejectsInvalidEmail() throws Exception {
        register(OPEN_COURSE, "Clara Test", "keine-mail")
                .andExpect(status().isBadRequest());
    }

    @Test
    void returnsNotFoundForUnknownCourse() throws Exception {
        register(999, "Clara Test", "clara@example.org")
                .andExpect(status().isNotFound())
                .andExpect(openApi().isValid(CONTRACT));
    }

    @Test
    void cancellingFreesAPlace() throws Exception {
        long id = idOf(register(OPEN_COURSE, "Clara Test", "clara@example.org"));

        mvc.perform(delete("/api/registrations/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(openApi().isValid(CONTRACT));

        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 1)].freePlaces").value(12));
    }

    private long idOf(ResultActions result) throws Exception {
        return ((Number) JsonPath.read(result.andReturn().getResponse().getContentAsString(), "$.id")).longValue();
    }

    private ResultActions register(long courseId, String name, String email) throws Exception {
        return mvc.perform(post("/api/courses/{id}/registrations", courseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "%s", "email": "%s"}
                        """.formatted(name, email)));
    }
}
