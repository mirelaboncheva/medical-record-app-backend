package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;
import java.time.LocalDate;

@Data
public class SickLeaveDto {
    private Long sickLeaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long appointmentId;
    private Long patientId;
    private String patientName;
    private Long doctorId;
    private String doctorName;
    private String specialization;
}