package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.AppointmentResponse;
import com.mirela.medicalrecordapp.model.Patient;

import java.util.List;
import java.util.Map;

public interface AppointmentService {
    List<AppointmentResponse> getAppointments();
    AppointmentResponse getAppointmentById(Long id);
    List<AppointmentResponse> getAppointmentsByDoctorId(Long doctorId);
    List<AppointmentResponse> getAppointmentsByPatientId(Long patientId);
    List<Patient> getPatientsWithDiagnose(String diagnose);
    List<Map<String, Object>> countMostFrequentDiagnoses();
    List<Map<String, Object>> countAppointmentsForEveryDoctor();
    List<Map<String, Object>> getDoctorWithMostSickLeaves();
    void deleteAppointment(Long id);
}
