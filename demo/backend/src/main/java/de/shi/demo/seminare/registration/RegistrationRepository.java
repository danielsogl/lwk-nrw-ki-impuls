package de.shi.demo.seminare.registration;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    long countByCourseId(Long courseId);
}
