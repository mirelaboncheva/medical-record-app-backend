package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.*;
import com.mirela.medicalrecordapp.mapper.DoctorMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.model.enums.Status;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.doctor.MyAppointmentsService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyAppointmentsServiceImpl implements MyAppointmentsService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final DoctorMapper mapper;

    @PreAuthorize("hasRole('DOCTOR')")
    public List<MyAppointmentSummaryDto> getMyAppointments() {
        Long doctorId = getCurrentDoctorId();
        return appointmentRepository.findByDoctorIdOrderByAppointmentDateDesc(doctorId)
                .stream()
                .map(mapper::toSummaryDto)
                .toList();
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public MyAppointmentDetailsDto getMyAppointmentDetails(Long appointmentId) {
        Long doctorId = getCurrentDoctorId();
        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new SecurityException("Access denied");
        }
        return mapper.toDetailsDto(appointment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Appointment createAppointment(CreateAppointmentRequest request) {
        Long doctorId = getCurrentDoctorId();

        var doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        var patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        Appointment appointment = new Appointment();
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentHour(request.getAppointmentHour());
        appointment.setDuration(request.getDuration());
        appointment.setStatus(Status.SCHEDULED);

        return appointmentRepository.save(appointment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Appointment updateAppointment(Long appointmentId, UpdateAppointmentRequest request) {
        Long doctorId = getCurrentDoctorId();

        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new SecurityException("Access denied");
        }

        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentHour(request.getAppointmentHour());
        appointment.setDuration(request.getDuration());
        appointment.setStatus(request.getStatus());

        return appointmentRepository.save(appointment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public Appointment changeStatus(Long appointmentId, ChangeAppointmentStatusRequest request) {
        Long doctorId = getCurrentDoctorId();

        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new SecurityException("Access denied");
        }

        appointment.setStatus(request.getStatus());
        return appointmentRepository.save(appointment);
    }

    @PreAuthorize("hasRole('DOCTOR')")
    public void deleteAppointment(Long appointmentId) {
        Long doctorId = getCurrentDoctorId();

        var appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new SecurityException("Access denied");
        }

        appointmentRepository.delete(appointment);
    }

    private Long getCurrentDoctorId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((User) auth.getPrincipal()).getId();
        return doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"))
                .getId();
    }
}
