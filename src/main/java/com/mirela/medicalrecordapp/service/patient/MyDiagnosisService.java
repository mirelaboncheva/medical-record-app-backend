package com.mirela.medicalrecordapp.service.patient;

import com.mirela.medicalrecordapp.dto.patient.MyDiagnosisDto;

import java.util.List;

public interface MyDiagnosisService {

    List<MyDiagnosisDto> getMyDiagnoses();
}
