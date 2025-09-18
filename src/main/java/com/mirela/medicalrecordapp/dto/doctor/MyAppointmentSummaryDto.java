package com.mirela.medicalrecordapp.dto.doctor;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

@Data
public class MyAppointmentSummaryDto {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private String status;
    private String patientName;
    private String nationalId;
    private boolean personalPatient;
}