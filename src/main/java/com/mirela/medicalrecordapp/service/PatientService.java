package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.HealthInsuranceUpdateRequest;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientResponse> getPatients();
    PatientResponse getPatientById(Long id);
    Patient updateHealthInsurance(Long id, HealthInsuranceUpdateRequest request);
    void deletePatient(Long id);
}
