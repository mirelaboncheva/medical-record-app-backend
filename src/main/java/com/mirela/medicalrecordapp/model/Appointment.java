package com.mirela.medicalrecordapp.model;

import com.mirela.medicalrecordapp.model.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
    private LocalTime appointmentHour;

    @Column(nullable = false)
    @NotNull
    private Duration duration;

    @Column(nullable = false)
    @NotNull
    private Status status;

    @OneToOne(mappedBy = "appointment")
    private SickLeave sickLeave;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Set<Diagnosis> diagnoses;

    @OneToMany(mappedBy = "appointment", fetch = FetchType.LAZY)
    private Set<Treatment> treatments;
}