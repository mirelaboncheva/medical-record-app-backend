package com.mirela.medicalrecordapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatientPersonalDataResponse {
    private String nationalId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
