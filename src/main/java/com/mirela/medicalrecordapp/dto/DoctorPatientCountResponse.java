package com.mirela.medicalrecordapp.dto;

import com.mirela.medicalrecordapp.model.Doctor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoctorPatientCountResponse {

    private DoctorResponse doctorResponse;
    private Long patientCount;
}
