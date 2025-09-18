package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MyTreatmentDto {
    private String medicine;
    private String dosage;
    private String duration;
    private String doctorNotes;
    private LocalDate appointmentDate;
    private String doctorName;
    private String specialization;
}
