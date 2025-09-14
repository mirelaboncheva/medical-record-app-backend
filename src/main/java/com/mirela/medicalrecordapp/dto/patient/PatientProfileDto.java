package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;

@Data
public class PatientProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String nationalId;
    private boolean healthInsurancePaid;

    private String personalDoctorName;
    private String personalDoctorSpecialization;
    private String personalDoctorPhone;
}