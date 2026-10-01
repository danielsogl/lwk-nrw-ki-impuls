package de.shi.demo.seminare.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(@NotBlank String name, @NotBlank @Email String email) {
}
