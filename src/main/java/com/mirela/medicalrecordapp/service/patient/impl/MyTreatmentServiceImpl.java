package com.mirela.medicalrecordapp.service.patient.impl;

import com.mirela.medicalrecordapp.dto.patient.MyTreatmentDto;
import com.mirela.medicalrecordapp.mapper.TreatmentMapper;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.repository.TreatmentRepository;
import com.mirela.medicalrecordapp.service.patient.MyTreatmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyTreatmentServiceImpl implements MyTreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final PatientRepository patientRepository;
    private final TreatmentMapper mapper;

    @PreAuthorize("hasRole('PATIENT')")
    public List<MyTreatmentDto> getMyTreatments() {
        Long patientId = getCurrentPatientId();
        return treatmentRepository.findByAppointmentPatientIdOrderByAppointmentAppointmentDateDesc(patientId)
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
