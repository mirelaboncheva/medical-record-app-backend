package com.mirela.medicalrecordapp.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateDiagnosisRequest {

    @NotBlank(message = "Diagnosis name is required")
    private String name;
}