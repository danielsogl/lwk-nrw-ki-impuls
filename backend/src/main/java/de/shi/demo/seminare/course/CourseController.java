package de.shi.demo.seminare.course;

import de.shi.demo.seminare.registration.RegistrationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
class CourseController {

    private final CourseRepository courses;
    private final RegistrationRepository registrations;

    CourseController(CourseRepository courses, RegistrationRepository registrations) {
        this.courses = courses;
        this.registrations = registrations;
    }

    @GetMapping
    List<CourseDto> list() {
        return courses.findAllByOrderByStartDateAsc().stream()
                .map(c -> new CourseDto(c.getId(), c.getTitle(), c.getStartDate(), c.getLocation(), c.getCapacity(),
                        c.getCapacity() - (int) registrations.countByCourseId(c.getId())))
                .toList();
    }
}
