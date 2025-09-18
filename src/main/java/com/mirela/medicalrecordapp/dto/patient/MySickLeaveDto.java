package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MySickLeaveDto {
    private Long sickLeaveId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate appointmentDate;
    private Long doctorId;
    private String doctorName;
    private String specialization;
}
