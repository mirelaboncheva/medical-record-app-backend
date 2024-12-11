package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record CreatePatientRequest(
        String nationalId,
        String firstName,
        String lastName,
        String phoneNumber,
        String email
) {
}
