package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.dto.TreatmentResponse;

import java.util.List;

public interface TreatmentService {
    List<TreatmentResponse> getTreatments();
    TreatmentResponse getTreatmentById(Long id);
    void deleteTreatment(Long id);
}
