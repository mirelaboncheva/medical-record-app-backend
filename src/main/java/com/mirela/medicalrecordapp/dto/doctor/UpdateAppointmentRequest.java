package com.mirela.medicalrecordapp.dto.doctor;

import com.mirela.medicalrecordapp.model.enums.Status;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateAppointmentRequest {

    @NotNull(message = "Appointment date is required")
    @FutureOrPresent(message = "Appointment date must be today or in the future")
    private LocalDate appointmentDate;

    @NotNull(message = "Appointment hour is required")
    private LocalTime appointmentHour;

    @NotNull(message = "Duration is required")
    private Duration duration;

    @NotNull(message = "Status is required")
    private Status status;
}
