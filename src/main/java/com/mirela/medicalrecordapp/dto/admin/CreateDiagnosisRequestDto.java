package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;

@Data
public class CreateDiagnosisRequestDto {
    private Long appointmentId;
    private String name;
}