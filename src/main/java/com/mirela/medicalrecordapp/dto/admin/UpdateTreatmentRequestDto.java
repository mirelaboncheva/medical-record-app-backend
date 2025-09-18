package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;

@Data
public class UpdateTreatmentRequestDto {
    private String medicine;
    private String dosage;
    private String duration;
    private String doctorNotes;
}