package com.mirela.medicalrecordapp.model;

import com.mirela.medicalrecordapp.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    @Column(nullable = false)
    @NotNull
    private LocalDate appointmentDate;

    @Column(nullable = false)
    @NotNull
    private Status status;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    private SickLeave sickLeave;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL)
    private List<Treatment> treatments;
}