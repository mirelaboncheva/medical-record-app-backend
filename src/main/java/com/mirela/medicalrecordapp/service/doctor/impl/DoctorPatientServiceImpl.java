package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.PatientListDto;
import com.mirela.medicalrecordapp.mapper.PatientListMapper;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.doctor.DoctorPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorPatientServiceImpl implements DoctorPatientService {

    private final PatientRepository patientRepository;
    private final PatientListMapper mapper;

    @PreAuthorize("hasRole('DOCTOR')")
    public List<PatientListDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
