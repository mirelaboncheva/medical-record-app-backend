package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.HealthInsuranceUpdateRequest;
import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.dto.PatientRequest;
import com.mirela.medicalrecordapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientPersonalDataResponse> getPatients();
    PatientPersonalDataResponse getPatientById(Long id);
    Patient updateHealthInsurance(Long id, HealthInsuranceUpdateRequest request);
    void deletePatient(Long id);
}
