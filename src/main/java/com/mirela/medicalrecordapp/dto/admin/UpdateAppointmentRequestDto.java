package com.mirela.medicalrecordapp.dto.admin;

import com.mirela.medicalrecordapp.model.enums.Status;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class UpdateAppointmentRequestDto {
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private Status status;
}