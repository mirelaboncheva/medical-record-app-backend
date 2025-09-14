package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreateDiagnosisRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DiagnosisDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDiagnosisRequestDto;
import com.mirela.medicalrecordapp.mapper.DiagnosisMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DiagnosisRepository;
import com.mirela.medicalrecordapp.service.admin.AdminDiagnosisService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDiagnosisServiceImpl implements AdminDiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;
    private final DiagnosisMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<DiagnosisDto> getAllDiagnoses() {
        return diagnosisRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DiagnosisDto getDiagnosisById(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        return mapper.toDto(diagnosis);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public DiagnosisDto createDiagnosis(CreateDiagnosisRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(dto.getName());
        diagnosis.setAppointment(appointment);

        diagnosisRepository.save(diagnosis);
        return mapper.toDto(diagnosis);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public DiagnosisDto updateDiagnosis(Long id, UpdateDiagnosisRequestDto dto) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));

        diagnosis.setName(dto.getName());
        return mapper.toDto(diagnosis);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDiagnosis(Long id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));
        diagnosisRepository.delete(diagnosis);
    }
}