package de.shi.demo.seminare.registration;

public record RegistrationDto(Long id, Long courseId, String name, String email, RegistrationStatus status,
                              Integer waitlistPosition) {

    static RegistrationDto from(Registration r, Integer waitlistPosition) {
        return new RegistrationDto(r.getId(), r.getCourse().getId(), r.getName(), r.getEmail(), r.getStatus(),
                waitlistPosition);
    }
}
