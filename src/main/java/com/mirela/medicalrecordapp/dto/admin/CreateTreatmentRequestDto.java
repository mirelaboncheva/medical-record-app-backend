package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;

@Data
public class CreateTreatmentRequestDto {
    private Long appointmentId;
    private String medicine;
    private String dosage;
    private String duration; // e.g., "7 days", "2 weeks"
    private String doctorNotes;
}