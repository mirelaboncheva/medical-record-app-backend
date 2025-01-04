package com.mirela.medicalrecordapp.model;

import com.mirela.medicalrecordapp.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(
        name="gp_assignments",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"patient_id"})
        }
)
@Getter
@Setter
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