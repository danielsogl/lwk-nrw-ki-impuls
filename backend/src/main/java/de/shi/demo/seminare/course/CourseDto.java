package de.shi.demo.seminare.course;

import java.time.LocalDate;

public record CourseDto(Long id, String title, LocalDate startDate, String location, int capacity, int freePlaces) {
}
