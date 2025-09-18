package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;

@Data
public class TreatmentDto {
    private Long treatmentId;
    private String medicine;
    private String dosage;
    private String duration;
    private String doctorNotes;
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String specialization;
}