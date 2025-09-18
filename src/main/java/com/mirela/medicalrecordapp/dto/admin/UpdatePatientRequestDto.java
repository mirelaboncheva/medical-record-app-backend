package com.mirela.medicalrecordapp.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientRequestDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private boolean healthInsurancePaid;
    private Long personalDoctorId;
}