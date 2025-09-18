package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @NotNull(message = "Patient must not be null")
    @OneToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "patient_id", nullable = false, unique = true)
    private Patient patient;

    @NotNull(message = "Doctor must not be null")
    @ManyToOne(optional = false, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @NotNull(message = "Registration date cannot be null")
    @Column(nullable = false)
    @NotNull(message = "Start date cannot be null")
    private LocalDate registrationDate;

    @Column
    @Future(message = "End date must be in the future")
    private LocalDate deregistrationDate;
}