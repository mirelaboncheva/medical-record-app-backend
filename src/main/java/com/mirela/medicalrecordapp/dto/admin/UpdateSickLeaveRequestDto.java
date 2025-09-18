package com.mirela.medicalrecordapp.dto.admin;

import lombok.Data;
import java.time.LocalDate;

@Data
public class UpdateSickLeaveRequestDto {
    private LocalDate startDate;
    private LocalDate endDate;
}