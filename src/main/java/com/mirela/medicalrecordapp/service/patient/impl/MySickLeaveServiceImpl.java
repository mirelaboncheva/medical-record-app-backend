package com.mirela.medicalrecordapp.service.patient.impl;

import com.mirela.medicalrecordapp.dto.patient.MySickLeaveDto;
import com.mirela.medicalrecordapp.mapper.SickLeaveMapper;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.repository.SickLeaveRepository;
import com.mirela.medicalrecordapp.service.patient.MySickLeaveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MySickLeaveServiceImpl implements MySickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final PatientRepository patientRepository;
    private final SickLeaveMapper mapper;

    @PreAuthorize("hasRole('PATIENT')")
    public List<MySickLeaveDto> getMySickLeaves() {
        Long patientId = getCurrentPatientId();
        return sickLeaveRepository.findByAppointmentPatientIdOrderByAppointmentAppointmentDateDesc(patientId)
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