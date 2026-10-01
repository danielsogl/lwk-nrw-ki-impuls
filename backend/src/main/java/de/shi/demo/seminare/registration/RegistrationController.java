package de.shi.demo.seminare.registration;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RegistrationController {

    private final RegistrationService service;

    RegistrationController(RegistrationService service) {
        this.service = service;
    }

    @PostMapping("/api/courses/{courseId}/registrations")
    @ResponseStatus(HttpStatus.CREATED)
    RegistrationDto register(@PathVariable Long courseId, @Valid @RequestBody RegistrationRequest request) {
        return RegistrationDto.from(service.register(courseId, request));
    }

    @DeleteMapping("/api/registrations/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void cancel(@PathVariable Long id) {
        service.cancel(id);
    }
}
