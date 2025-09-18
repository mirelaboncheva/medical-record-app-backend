package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreateSickLeaveRequestDto;
import com.mirela.medicalrecordapp.dto.admin.SickLeaveDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateSickLeaveRequestDto;
import com.mirela.medicalrecordapp.mapper.SickLeaveMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.SickLeave;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.SickLeaveRepository;
import com.mirela.medicalrecordapp.service.admin.AdminSickLeaveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminSickLeaveServiceImpl implements AdminSickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final AppointmentRepository appointmentRepository;
    private final SickLeaveMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<SickLeaveDto> getAllSickLeaves() {
        return sickLeaveRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public SickLeaveDto getSickLeaveById(Long id) {
        SickLeave sickLeave = sickLeaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found"));
        return mapper.toDto(sickLeave);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public SickLeaveDto createSickLeave(CreateSickLeaveRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (appointment.getSickLeave() != null) {
            throw new IllegalStateException("This appointment already has a sick leave");
        }

        SickLeave sickLeave = new SickLeave();
        sickLeave.setStartDate(dto.getStartDate());
        sickLeave.setEndDate(dto.getEndDate());
        sickLeave.setAppointment(appointment);

        sickLeaveRepository.save(sickLeave);
        appointment.setSickLeave(sickLeave);

        return mapper.toDto(sickLeave);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public SickLeaveDto updateSickLeave(Long id, UpdateSickLeaveRequestDto dto) {
        SickLeave sickLeave = sickLeaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found"));

        sickLeave.setStartDate(dto.getStartDate());
        sickLeave.setEndDate(dto.getEndDate());

        return mapper.toDto(sickLeave);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSickLeave(Long id) {
        SickLeave sickLeave = sickLeaveRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found"));
        sickLeaveRepository.delete(sickLeave);
    }
}