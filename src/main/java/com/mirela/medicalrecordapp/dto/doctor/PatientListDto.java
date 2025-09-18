package com.mirela.medicalrecordapp.dto.doctor;

import lombok.Data;

@Data
public class PatientListDto {
    private String firstName;
    private String lastName;
    private String nationalId;
    private boolean healthInsurancePaid;
    private String personalDoctorUid;
    private String personalDoctorName;
    private String personalDoctorSpecialization;
}
