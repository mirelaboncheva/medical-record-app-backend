package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

@Data
public class MyVisitSummaryDto {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private String status;
    private String doctorName;
    private String specialization;
    private boolean personalDoctor;
}