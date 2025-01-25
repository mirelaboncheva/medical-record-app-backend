package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="diagnosis")
public class Diagnosis{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name = "appointment_id") //nullable = false ?
    private Appointment appointment;
}