package com.mirela.medicalrecordapp.model;

import com.mirela.medicalrecordapp.model.common.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "sick_leave")
@Getter
@Setter
public class SickLeave extends BaseEntity {
    @Column(nullable = false)
    @NotNull
    private LocalDate startDate;

    @Column(nullable = false)
    @NotNull
    @FutureOrPresent(message = "End date must be in the future or present")
    private LocalDate endDate;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;
}