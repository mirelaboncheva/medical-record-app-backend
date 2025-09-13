package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.mapper.PatientMapper;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.admin.AdminPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPatientServiceImpl implements AdminPatientService {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<ManagePatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toManageDto)
                .toList();
    }
}
