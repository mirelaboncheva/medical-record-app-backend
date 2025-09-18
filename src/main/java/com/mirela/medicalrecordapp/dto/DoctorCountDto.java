package com.mirela.medicalrecordapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorCountDto {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private Long count;
}