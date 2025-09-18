package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DiagnosisCountDto;
import com.mirela.medicalrecordapp.dto.PatientBasicDto;
import com.mirela.medicalrecordapp.dto.DoctorCountDto;
import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
public class ReportController {

    private final ReportService service;

    @GetMapping("/patients-with-diagnosis")
    public List<PatientBasicDto> patientsWithDiagnosis(@RequestParam String name) {
        return service.patientsWithDiagnosis(name);
    }

    @GetMapping("/most-common-diagnoses")
    public List<DiagnosisCountDto> mostCommonDiagnoses() {
        return service.mostCommonDiagnoses();
    }

    @GetMapping("/patients-by-doctor/{doctorId}")
    public List<PatientBasicDto> patientsByPersonalDoctor(@PathVariable Long doctorId) {
        return service.patientsByPersonalDoctor(doctorId);
    }

    @GetMapping("/patient-count-per-doctor")
    public List<DoctorCountDto> patientCountPerDoctor() {
        return service.patientCountPerDoctor();
    }

    @GetMapping("/visit-count-per-doctor")
    public List<DoctorCountDto> visitCountPerDoctor() {
        return service.visitCountPerDoctor();
    }

    @GetMapping("/visits-by-patient/{patientId}")
    public List<AppointmentDto> visitsByPatient(@PathVariable Long patientId) {
        return service.visitsByPatient(patientId);
    }

    @GetMapping("/exams-all-doctors")
    public List<AppointmentDto> examsAllDoctorsInPeriod(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.examsAllDoctorsInPeriod(start, end);
    }

    @GetMapping("/exams-by-doctor/{doctorId}")
    public List<AppointmentDto> examsByDoctorInPeriod(
            @PathVariable Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return service.examsByDoctorInPeriod(doctorId, start, end);
    }

    @GetMapping("/month-most-sick-leaves")
    public String monthWithMostSickLeaves() {
        return service.monthWithMostSickLeaves();
    }

    @GetMapping("/doctor-most-sick-leaves")
    public List<String> doctorWithMostSickLeaves() {
        return service.doctorWithMostSickLeaves();
    }
}
