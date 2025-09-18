package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateDiagnosisRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateDiagnosisRequest;
import com.mirela.medicalrecordapp.model.Diagnosis;

public interface ManageDiagnosisService {

    Diagnosis addDiagnosis(Long appointmentId, CreateDiagnosisRequest request);
    Diagnosis updateDiagnosis(Long diagnosisId, UpdateDiagnosisRequest request);
    void deleteDiagnosis(Long diagnosisId);
}