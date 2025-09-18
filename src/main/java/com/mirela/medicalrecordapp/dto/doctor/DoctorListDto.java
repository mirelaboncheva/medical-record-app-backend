package com.mirela.medicalrecordapp.dto.doctor;

import lombok.Data;

@Data
public class DoctorListDto {
    private Long doctorId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String doctorUid;
    private String specialization;
}