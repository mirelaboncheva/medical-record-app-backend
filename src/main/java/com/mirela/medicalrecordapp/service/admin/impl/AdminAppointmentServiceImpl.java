package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.dto.admin.CreateAppointmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateAppointmentRequestDto;
import com.mirela.medicalrecordapp.mapper.AppointmentMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.admin.AdminAppointmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminAppointmentServiceImpl implements AdminAppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<AppointmentDto> getAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AppointmentDto getAppointmentById(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        return mapper.toDto(appointment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public AppointmentDto createAppointment(CreateAppointmentRequestDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentHour(dto.getAppointmentHour());
        appointment.setDuration(dto.getDuration());
        appointment.setStatus(dto.getStatus());

        appointmentRepository.save(appointment);
        return mapper.toDto(appointment);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public AppointmentDto updateAppointment(Long id, UpdateAppointmentRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        appointment.setAppointmentDate(dto.getAppointmentDate());
        appointment.setAppointmentHour(dto.getAppointmentHour());
        appointment.setDuration(dto.getDuration());
        appointment.setStatus(dto.getStatus());

        return mapper.toDto(appointment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }
}