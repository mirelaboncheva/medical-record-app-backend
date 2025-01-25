package com.mirela.medicalrecordapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDTO {

    private String medicine;
    private String dosage;
    private String duration;
    private String doctorNotes;
}
