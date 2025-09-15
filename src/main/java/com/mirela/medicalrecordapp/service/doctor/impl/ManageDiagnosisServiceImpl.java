package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.CreateDiagnosisRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateDiagnosisRequest;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DiagnosisRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.service.doctor.ManageDiagnosisService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageDiagnosisServiceImpl implements ManageDiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @PreAuthorize("hasRole('DOCTOR')")
    public Diagnosis addDiagnosis(Long appointmentId, CreateDiagnosisRequest request) {
        Appointment appointment = getDoctorOwnedAppointment(appointmentId);

        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setName(request.getName());
        diagnosis.setAppointment(appointment);

        return diagnosisRepository.save(diagnosis);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Diagnosis updateDiagnosis(Long diagnosisId, UpdateDiagnosisRequest request) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));

        getDoctorOwnedAppointment(diagnosis.getAppointment().getId());

        diagnosis.setName(request.getName());
        return diagnosisRepository.save(diagnosis);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public void deleteDiagnosis(Long diagnosisId) {
        Diagnosis diagnosis = diagnosisRepository.findById(diagnosisId)
                .orElseThrow(() -> new EntityNotFoundException("Diagnosis not found"));

        getDoctorOwnedAppointment(diagnosis.getAppointment().getId());

        diagnosisRepository.delete(diagnosis);
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
