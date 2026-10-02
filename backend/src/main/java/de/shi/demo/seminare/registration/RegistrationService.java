package de.shi.demo.seminare.registration;

import de.shi.demo.seminare.course.Course;
import de.shi.demo.seminare.course.CourseRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Clock;
import java.time.Instant;

@Service
public class RegistrationService {

    private final CourseRepository courses;
    private final RegistrationRepository registrations;
    private final Clock clock;

    RegistrationService(CourseRepository courses, RegistrationRepository registrations, Clock clock) {
        this.courses = courses;
        this.registrations = registrations;
        this.clock = clock;
    }

    @Transactional
    public Registration register(Long courseId, RegistrationRequest request) {
        Course course = courses.findForUpdateById(courseId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Kurs nicht gefunden"));
        if (StringUtils.isBlank(request.name()) || StringUtils.isBlank(request.email())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Name und E-Mail sind Pflichtfelder");
        }
        RegistrationStatus status = registrations.confirmedCount(courseId) < course.getCapacity()
                ? RegistrationStatus.CONFIRMED
                : RegistrationStatus.WAITLISTED;
        return registrations.save(new Registration(course, request.name(), request.email(), Instant.now(clock), status));
    }

    /** Platz auf der Warteliste ab 1, {@code null} bei fester Anmeldung. */
    @Transactional(readOnly = true)
    public Integer waitlistPosition(Registration registration) {
        if (registration.getStatus() != RegistrationStatus.WAITLISTED) {
            return null;
        }
        return registrations.waitlist(registration.getCourse().getId()).indexOf(registration) + 1;
    }

    @Transactional
    public void cancel(Long registrationId) {
        Registration registration = registrations.findById(registrationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anmeldung nicht gefunden"));
        registrations.delete(registration);
        if (registration.getStatus() == RegistrationStatus.CONFIRMED) {
            registrations.waitlist(registration.getCourse().getId()).stream()
                    .findFirst()
                    .ifPresent(Registration::confirm);
        }
    }
}
