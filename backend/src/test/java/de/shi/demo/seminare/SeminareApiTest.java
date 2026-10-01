package de.shi.demo.seminare;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    MockMvc mvc;

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
                .andExpect(openApi().isValid(CONTRACT));

        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 1)].freePlaces").value(11));
    }

    @Test
    void rejectsRegistrationWhenCourseIsFull() throws Exception {
        register(FULL_COURSE, "Clara Test", "clara@example.org")
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.title").value("Kurs ausgebucht"))
                .andExpect(openApi().isValid(CONTRACT));
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
        String body = register(OPEN_COURSE, "Clara Test", "clara@example.org")
                .andReturn().getResponse().getContentAsString();
        long id = JsonPath.<Number>read(body, "$.id").longValue();

        mvc.perform(delete("/api/registrations/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(openApi().isValid(CONTRACT));

        mvc.perform(get("/api/courses"))
                .andExpect(jsonPath("$[?(@.id == 1)].freePlaces").value(12));
    }

    private ResultActions register(long courseId, String name, String email) throws Exception {
        return mvc.perform(post("/api/courses/{id}/registrations", courseId)
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                        {"name": "%s", "email": "%s"}
                        """.formatted(name, email)));
    }
}
