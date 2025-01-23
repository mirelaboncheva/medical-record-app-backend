package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DiagnosisResponse;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosisResponse> getDiagnoses();
    DiagnosisResponse getDiagnosisById(Long id);
    DiagnosisResponse getDiagnosisByName(String name);
    void deleteDiagnosis(Long id);
}
