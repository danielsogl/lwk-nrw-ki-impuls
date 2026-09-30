package de.shi.demo.seminare.registration;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    long countByCourseIdAndStatus(Long courseId, RegistrationStatus status);

    List<Registration> findByCourseIdAndStatusOrderByCreatedAtDesc(Long courseId, RegistrationStatus status);

    default List<Registration> waitlist(Long courseId) {
        return findByCourseIdAndStatusOrderByCreatedAtDesc(courseId, RegistrationStatus.WAITLISTED);
    }

    default long confirmedCount(Long courseId) {
        return countByCourseIdAndStatus(courseId, RegistrationStatus.CONFIRMED);
    }
}
