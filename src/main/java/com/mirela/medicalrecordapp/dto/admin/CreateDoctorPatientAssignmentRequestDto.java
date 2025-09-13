package com.mirela.medicalrecordapp.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateDoctorPatientAssignmentRequestDto {
    private Long patientId;
    private Long doctorId;
    private LocalDate registrationDate; // optional, defaults to now
}