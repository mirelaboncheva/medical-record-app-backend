package com.mirela.medicalrecordapp.dto.doctor;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateTreatmentRequest {

    @NotBlank(message = "Medicine name is required")
    private String medicine;

    @NotBlank(message = "Dosage is required")
    private String dosage;

    @NotBlank(message = "Duration is required")
    private String duration;

    private String doctorNotes;
}
