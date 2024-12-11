package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record GPAssignmentDTO(
        PatientInfo patientInfo,
        DoctorInfo doctorInfo
) {
}
