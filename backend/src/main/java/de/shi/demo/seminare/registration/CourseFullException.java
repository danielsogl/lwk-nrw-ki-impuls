package de.shi.demo.seminare.registration;

import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponseException;

class CourseFullException extends ErrorResponseException {

    CourseFullException() {
        super(HttpStatus.CONFLICT);
        getBody().setTitle("Kurs ausgebucht");
        getBody().setDetail("Für diesen Kurs sind keine Plätze mehr frei.");
    }
}
