package de.shi.demo.seminare.registration;

public record RegistrationDto(Long id, Long courseId, String name, String email) {

    static RegistrationDto from(Registration r) {
        return new RegistrationDto(r.getId(), r.getCourse().getId(), r.getName(), r.getEmail());
    }
}
