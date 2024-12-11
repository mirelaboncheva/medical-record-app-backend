package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record TreatmentDTO(
        String medicine,
        String dosage,
        String duration
) {
}
