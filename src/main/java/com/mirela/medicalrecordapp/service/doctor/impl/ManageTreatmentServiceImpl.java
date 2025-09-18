package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.CreateTreatmentRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateTreatmentRequest;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Treatment;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.TreatmentRepository;
import com.mirela.medicalrecordapp.service.doctor.ManageTreatmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManageTreatmentServiceImpl implements ManageTreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;

    @PreAuthorize("hasRole('DOCTOR')")
    public Treatment addTreatment(Long appointmentId, CreateTreatmentRequest request) {
        Appointment appointment = getDoctorOwnedAppointment(appointmentId);

        Treatment treatment = new Treatment();
        treatment.setMedicine(request.getMedicine());
        treatment.setDosage(request.getDosage());
        treatment.setDuration(request.getDuration());
        treatment.setDoctorNotes(request.getDoctorNotes());
        treatment.setAppointment(appointment);

        return treatmentRepository.save(treatment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Treatment updateTreatment(Long treatmentId, UpdateTreatmentRequest request) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found"));

        // Security check
        getDoctorOwnedAppointment(treatment.getAppointment().getId());

        treatment.setMedicine(request.getMedicine());
        treatment.setDosage(request.getDosage());
        treatment.setDuration(request.getDuration());
        treatment.setDoctorNotes(request.getDoctorNotes());

        return treatmentRepository.save(treatment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public void deleteTreatment(Long treatmentId) {
        Treatment treatment = treatmentRepository.findById(treatmentId)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found"));

        // Security check
        getDoctorOwnedAppointment(treatment.getAppointment().getId());

        treatmentRepository.delete(treatment);
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
