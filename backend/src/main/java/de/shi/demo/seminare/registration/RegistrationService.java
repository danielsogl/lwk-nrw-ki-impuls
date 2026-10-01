package de.shi.demo.seminare.registration;

import de.shi.demo.seminare.course.Course;
import de.shi.demo.seminare.course.CourseRepository;
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
        if (registrations.countByCourseId(courseId) >= course.getCapacity()) {
            throw new CourseFullException();
        }
        return registrations.save(new Registration(course, request.name(), request.email(), Instant.now(clock)));
    }

    @Transactional
    public void cancel(Long registrationId) {
        Registration registration = registrations.findById(registrationId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Anmeldung nicht gefunden"));
        registrations.delete(registration);
    }
}
