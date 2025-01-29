package com.mirela.medicalrecordapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private String doctorUid;
    private UserDataResponse userData;
}
