package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DiagnosisRequest;
import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.model.Diagnosis;

import java.util.List;

public interface DiagnosisService {
    List<DiagnosisResponse> getDiagnoses();
    DiagnosisResponse getDiagnosisById(Long id);
    DiagnosisResponse getDiagnosisByName(String name);
    Diagnosis saveDiagnosis(DiagnosisRequest diagnosisRequest);
    Diagnosis updateDiagnosis(Long id, DiagnosisRequest diagnosisRequest);
    void deleteDiagnosis(Long id);
}
