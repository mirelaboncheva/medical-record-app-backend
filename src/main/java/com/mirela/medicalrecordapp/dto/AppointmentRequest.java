package com.mirela.medicalrecordapp.dto;

import com.mirela.medicalrecordapp.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequest {

    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Integer durationInMinutes;
    private Status status;
    private Long doctorId;
    private Long patientId;
}
