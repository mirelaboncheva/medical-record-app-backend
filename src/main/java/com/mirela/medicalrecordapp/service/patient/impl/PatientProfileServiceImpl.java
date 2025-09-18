package com.mirela.medicalrecordapp.service.patient.impl;

import com.mirela.medicalrecordapp.dto.patient.PatientProfileDto;
import com.mirela.medicalrecordapp.mapper.PatientMapper;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.patient.PatientProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientProfileServiceImpl implements PatientProfileService {

    private final PatientRepository patientRepository;
    private final PatientMapper mapper;

    @PreAuthorize("hasRole('PATIENT')")
    public PatientProfileDto getMyProfile() {
        Long userId = getCurrentUserId();
        var patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return mapper.toDto(patient);
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Adjust this cast if you use a custom UserDetails implementation
        return ((User) auth.getPrincipal()).getId();
    }
}