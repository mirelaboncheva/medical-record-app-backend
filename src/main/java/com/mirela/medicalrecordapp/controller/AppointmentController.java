package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.AppointmentResponse;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity<List<AppointmentResponse>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponse> getAppointmentById(@PathVariable Long id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByDoctor(@PathVariable Long doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctorId(doctorId));
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<AppointmentResponse>> getAppointmentsByPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatientId(patientId));
    }

    @GetMapping("/diagnose/{diagnose}")
    public ResponseEntity<List<Patient>> getPatientsWithDiagnose(@PathVariable String diagnose) {
        return ResponseEntity.ok(appointmentService.getPatientsWithDiagnose(diagnose));
    }

    @GetMapping("/diagnoses/frequent")
    public ResponseEntity<List<Map<String, Object>>> countMostFrequentDiagnoses() {
        return ResponseEntity.ok(appointmentService.countMostFrequentDiagnoses());
    }

    @GetMapping("/doctor/appointments")
    public ResponseEntity<List<Map<String, Object>>> countAppointmentsForEveryDoctor() {
        return ResponseEntity.ok(appointmentService.countAppointmentsForEveryDoctor());
    }

    @GetMapping("/doctor/sick-leaves")
    public ResponseEntity<List<Map<String, Object>>> getDoctorsWithMostSickLeaves() {
        return ResponseEntity.ok(appointmentService.getDoctorWithMostSickLeaves());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
