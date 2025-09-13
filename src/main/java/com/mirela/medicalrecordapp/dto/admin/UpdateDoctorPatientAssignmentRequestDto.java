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
public class UpdateDoctorPatientAssignmentRequestDto {
    private Long doctorId; // for reassignment
    private LocalDate deregistrationDate; // optional
}