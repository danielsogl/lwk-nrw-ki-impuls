package de.shi.demo.seminare.registration;

import de.shi.demo.seminare.course.Course;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.time.Instant;

@Entity
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Course course;

    private String name;

    private String email;

    private Instant createdAt;

    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    protected Registration() {
    }

    public Registration(Course course, String name, String email, Instant createdAt, RegistrationStatus status) {
        this.course = course;
        this.name = name;
        this.email = email;
        this.createdAt = createdAt;
        this.status = status;
    }

    void confirm() {
        this.status = RegistrationStatus.CONFIRMED;
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public RegistrationStatus getStatus() {
        return status;
    }
}
