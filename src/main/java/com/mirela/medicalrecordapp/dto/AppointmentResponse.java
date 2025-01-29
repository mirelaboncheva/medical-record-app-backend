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
public class AppointmentResponse {

    private PatientResponse patientResponse;
    private DoctorResponse doctorResponse;
    private LocalDate date;
    private LocalTime time;
    private Status status;
}
