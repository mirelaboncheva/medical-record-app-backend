package com.mirela.medicalrecordapp.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
public record PatientDTO(
    Long nationalId,
    String firstName,
    String lastName,
    String phoneNumber,
    String email
){};
