package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record PatientInfo(
        Long id,
        String firstName,
        String lastName
) {
}
