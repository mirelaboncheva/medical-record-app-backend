package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.CreateSickLeaveRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateSickLeaveRequest;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.SickLeave;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.SickLeaveRepository;
import com.mirela.medicalrecordapp.service.doctor.ManageSickLeaveService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageSickLeaveServiceImpl implements ManageSickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @PreAuthorize("hasRole('DOCTOR')")
    public SickLeave issueSickLeave(Long appointmentId, CreateSickLeaveRequest request) {
        Appointment appointment = getDoctorOwnedAppointment(appointmentId);

        SickLeave sickLeave = new SickLeave();
        sickLeave.setStartDate(request.getStartDate());
        sickLeave.setEndDate(request.getEndDate());
        sickLeave.setAppointment(appointment);

        return sickLeaveRepository.save(sickLeave);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public SickLeave updateSickLeave(Long sickLeaveId, UpdateSickLeaveRequest request) {
        SickLeave sickLeave = sickLeaveRepository.findById(sickLeaveId)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found"));

        getDoctorOwnedAppointment(sickLeave.getAppointment().getId());

        sickLeave.setStartDate(request.getStartDate());
        sickLeave.setEndDate(request.getEndDate());

        return sickLeaveRepository.save(sickLeave);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public void deleteSickLeave(Long sickLeaveId) {
        SickLeave sickLeave = sickLeaveRepository.findById(sickLeaveId)
                .orElseThrow(() -> new EntityNotFoundException("Sick leave not found"));

        getDoctorOwnedAppointment(sickLeave.getAppointment().getId());

        sickLeaveRepository.delete(sickLeave);
    }

    private Appointment getDoctorOwnedAppointment(Long appointmentId) {
        Long doctorId = getCurrentDoctorId();
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new SecurityException("Access denied");
        }
        return appointment;
    }

    private Long getCurrentDoctorId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) auth.getPrincipal()).getId();
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"))
                .getId();
    }
}
