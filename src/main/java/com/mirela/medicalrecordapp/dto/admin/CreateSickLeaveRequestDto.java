package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;
import java.time.LocalDate;

@Data
public class CreateSickLeaveRequestDto {
    private Long appointmentId;
    private LocalDate startDate;
    private LocalDate endDate;
}