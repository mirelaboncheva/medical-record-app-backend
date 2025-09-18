package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;

@Data
public class DiagnosisDto {
    private Long diagnosisId;
    private String name;
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String specialization;
}