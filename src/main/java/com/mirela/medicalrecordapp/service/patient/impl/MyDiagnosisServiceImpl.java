package com.mirela.medicalrecordapp.service.patient.impl;

import com.mirela.medicalrecordapp.dto.patient.MyDiagnosisDto;
import com.mirela.medicalrecordapp.mapper.DiagnosisMapper;
import com.mirela.medicalrecordapp.repository.DiagnosisRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.patient.MyDiagnosisService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyDiagnosisServiceImpl implements MyDiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final PatientRepository patientRepository;
    private final DiagnosisMapper mapper;

    @PreAuthorize("hasRole('PATIENT')")
    public List<MyDiagnosisDto> getMyDiagnoses() {
        Long patientId = getCurrentPatientId();
        return diagnosisRepository.findByAppointmentPatientIdOrderByAppointmentAppointmentDateDesc(patientId)
                .stream()
                .map(mapper::toDTo)
                .toList();
    }

    private Long getCurrentPatientId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((com.mirela.medicalrecordapp.model.User) auth.getPrincipal()).getId();
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"))
                .getId();
    }
}