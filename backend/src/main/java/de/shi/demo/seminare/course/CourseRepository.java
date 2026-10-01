package de.shi.demo.seminare.course;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findAllByOrderByStartDateAsc();

    /** Sperrt den Kurs bis zum Ende der Transaktion, damit gleichzeitige Anmeldungen nicht überbuchen. */
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Course> findForUpdateById(Long id);
}
