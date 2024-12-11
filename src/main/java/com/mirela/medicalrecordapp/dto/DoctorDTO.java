package com.mirela.medicalrecordapp.dto;

import lombok.Builder;

@Builder
public record DoctorDTO(
        Long doctorUid,
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String specialization
){
};
