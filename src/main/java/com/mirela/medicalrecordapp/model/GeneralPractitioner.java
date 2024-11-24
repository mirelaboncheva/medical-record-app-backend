package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(
        name="general_practitioner_assignments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id"})
        }
)
public class GeneralPractitioner extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDate registrationDate;

    @Column
    @NotNull(message = "End date cannot be null")
    @Future(message = "End date must be in the future")
    private LocalDate deregistrationDate;
}