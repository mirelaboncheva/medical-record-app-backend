package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="diagnosis")
@Getter
@Setter
public class Diagnosis extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}
