package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(
        name="doctor_patient_assignment",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id"})
        }
)
public class DoctorPatientAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDate registrationDate;

    @Column
    @Future(message = "End date must be in the future")
    private LocalDate deregistrationDate;
}