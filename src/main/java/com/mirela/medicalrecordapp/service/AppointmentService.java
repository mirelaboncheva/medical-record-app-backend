package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.AppointmentResponse;

import java.util.List;

public interface AppointmentService {
    List<AppointmentResponse> getAppointments();
    AppointmentResponse getAppointmentById(Long id);
    List<AppointmentResponse> getAppointmentsByDoctorId(Long doctorId);
    List<AppointmentResponse> getAppointmentsByPatientId(Long patientId);
    void deleteAppointment(Long id);
}
