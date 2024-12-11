package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.PatientDTO;
import com.mirela.medicalrecordapp.mapper.PatientMapper;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public List<PatientDTO> getPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patientMapper.toDTOList(patients);
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return patientRepository.findById(id)
                .map(patientMapper::toDTO)
                .orElse(null); //change null
    }

    @Override
    public Patient savePatient(PatientDTO patientDTO) {
        return patientRepository.save(patientMapper.toEntity(patientDTO));
    }

    @Override
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
