package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.AppointmentResponse;
import com.mirela.medicalrecordapp.mapper.AppointmentMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;

    @Override
    public List<AppointmentResponse> getAppointments() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointmentMapper.toDTOList(appointments);
    }

    @Override
    public AppointmentResponse getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(appointmentMapper::toDTO)
                .orElse(null); // TODO: Handle with a proper exception
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByDoctorId(Long doctorId) {
        List<Appointment> appointments = appointmentRepository.findByDoctorId(doctorId);
        return appointmentMapper.toDTOList(appointments);
    }

    @Override
    public List<AppointmentResponse> getAppointmentsByPatientId(Long patientId) {
        List<Appointment> appointments = appointmentRepository.findByPatientId(patientId);
        return appointmentMapper.toDTOList(appointments);
    }

//    @Override
//    public Appointment saveAppointment(AppointmentRequest appointmentRequest) {
//        return null;
//    }
//
//    @Override
//    public Appointment updateAppointment(Long id, AppointmentUpdateRequest appointmentUpdateRequest) {
//        return null;
//    }

    @Override
    public void deleteAppointment(Long id) {

    }
}
