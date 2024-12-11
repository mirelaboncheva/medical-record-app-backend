package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record DoctorInfo(
        Long id,
        String firstName,
        String lastName
) {
}
