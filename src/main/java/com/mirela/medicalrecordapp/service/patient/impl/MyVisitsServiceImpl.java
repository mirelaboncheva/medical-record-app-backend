package com.mirela.medicalrecordapp.service.patient.impl;

import com.mirela.medicalrecordapp.dto.patient.MyVisitDetailsDto;
import com.mirela.medicalrecordapp.dto.patient.MyVisitSummaryDto;
import com.mirela.medicalrecordapp.mapper.AppointmentMapper;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.patient.MyVisitsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyVisitsServiceImpl implements MyVisitsService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final AppointmentMapper mapper;

    @PreAuthorize("hasRole('PATIENT')")
    public List<MyVisitSummaryDto> getMyVisits() {
        Long patientId = getCurrentPatientId();
        return appointmentRepository.findByPatientIdOrderByAppointmentDateDesc(patientId)
                .stream()
                .map(mapper::toSummaryDto)
                .toList();
    }

    @PreAuthorize("hasRole('PATIENT')")
    public MyVisitDetailsDto getMyVisitDetails(Long appointmentId) {
        Long patientId = getCurrentPatientId();
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        if (!appointment.getPatient().getId().equals(patientId)) {
            throw new SecurityException("Access denied");
        }
        return mapper.toDetailsDto(appointment);
    }

    private Long getCurrentPatientId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((com.mirela.medicalrecordapp.model.User) auth.getPrincipal()).getId();
        return patientRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"))
                .getId();
    }
}
