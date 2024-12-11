package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.PatientDTO;
import com.mirela.medicalrecordapp.model.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getPatients();
    PatientDTO getPatientById(Long id);
    Patient savePatient(PatientDTO patientDTO);
    //PatientDTO updatePatient(PatientDTO patientDTO, Long id);
    void deletePatient(Long id);
}
