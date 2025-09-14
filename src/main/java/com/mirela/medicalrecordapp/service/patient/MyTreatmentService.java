package com.mirela.medicalrecordapp.service.patient;

import com.mirela.medicalrecordapp.dto.patient.MyTreatmentDto;

import java.util.List;

public interface MyTreatmentService {

    List<MyTreatmentDto> getMyTreatments();
}
