package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.HealthInsuranceUpdateRequest;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.mapper.PatientMapper;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;


    @Override
    public List<PatientResponse> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::toPatientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PatientResponse getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toPatientResponse)
                .orElse(null);
    }

    @Override
    public Patient updateHealthInsurance(Long id, HealthInsuranceUpdateRequest request) {
        Patient patient = patientRepository.findById(id).orElse(null); //TODO

        patient.setIsHealthInsurancePaid(request.getIsHealthInsurancePaid());

        return patientRepository.save(patient);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
