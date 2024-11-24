package com.mirela.medicalrecordapp.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="treatment")
@Getter
@Setter
public class Treatment extends BaseEntity{

    private String medicine;

    private String dosage;

    private String duration;

    private String doctorNotes;

    @ManyToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}
