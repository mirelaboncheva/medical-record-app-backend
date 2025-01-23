package com.mirela.medicalrecordapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GPResponse {

    private PatientResponse patientResponse;
    private DoctorResponse doctorResponse;
    private LocalDate registrationDate;
    private LocalDate deregistrationDate;

}
