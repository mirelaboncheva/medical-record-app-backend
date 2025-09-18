package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MyDiagnosisDto {
    private String name;
    private LocalDate appointmentDate;
    private String doctorName;
    private String specialization;
}