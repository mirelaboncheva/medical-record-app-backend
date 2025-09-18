package com.mirela.medicalrecordapp.dto.admin;

import com.mirela.medicalrecordapp.model.enums.Status;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDto {
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String specialization;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private Status status;
}