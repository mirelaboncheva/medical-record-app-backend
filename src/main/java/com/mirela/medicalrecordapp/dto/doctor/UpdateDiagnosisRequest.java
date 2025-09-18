package com.mirela.medicalrecordapp.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateDiagnosisRequest {

    @NotBlank(message = "Diagnosis name is required")
    private String name;
}