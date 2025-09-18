package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.DiagnosisCountDto;
import com.mirela.medicalrecordapp.dto.PatientBasicDto;
import com.mirela.medicalrecordapp.dto.DoctorCountDto;
import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.mapper.AppointmentMapper;
import com.mirela.medicalrecordapp.mapper.ReportMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.repository.ReportRepository;
import com.mirela.medicalrecordapp.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.Arrays.stream;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository repo;
    private final ReportMapper mapper;
    private final AppointmentMapper appointmentMapper;


    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<PatientBasicDto> patientsWithDiagnosis(String diagnosisName) {
        return repo.findPatientsWithDiagnosis(diagnosisName)
                .stream()
                .map(mapper::toPatientBasicDto)
                .toList();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<DiagnosisCountDto> mostCommonDiagnoses() {
        return repo.findMostCommonDiagnoses();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<PatientBasicDto> patientsByPersonalDoctor(Long doctorId) {
        return repo.findPatientsByPersonalDoctor(doctorId)
                .stream()
                .map(mapper::toPatientBasicDto)
                .toList();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<DoctorCountDto> patientCountPerDoctor() {
        return repo.countPatientsPerDoctor()
                .stream()
                .map(mapper::toDoctorCountDto)
                .toList();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<DoctorCountDto> visitCountPerDoctor() {
        return repo.countVisitsPerDoctor()
                .stream()
                .map(mapper::toDoctorCountDto)
                .toList();
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<AppointmentDto> visitsByPatient(Long patientId) {
        List<Appointment> entities = repo.findVisitsByPatient(patientId);
        return appointmentMapper.toDtoList(entities);
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<AppointmentDto> examsAllDoctorsInPeriod(LocalDate start, LocalDate end) {
        List<Appointment> entities = repo.findExamsAllDoctorsInPeriod(start, end);
        return appointmentMapper.toDtoList(entities);
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<AppointmentDto> examsByDoctorInPeriod(Long doctorId, LocalDate start, LocalDate end) {
        List<Appointment> entities = repo.findExamsByDoctorInPeriod(doctorId, start, end);
        return appointmentMapper.toDtoList(entities);
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public String monthWithMostSickLeaves() {
        var result = repo.monthWithMostSickLeaves();
        if (result.isEmpty()) return "No sick leaves found";
        var top = result.get(0);
        return "Month: " + top[0] + " with " + top[1] + " sick leaves";
    }

    @PreAuthorize("hasRole('DOCTOR') or hasRole('ADMIN')")
    public List<String> doctorWithMostSickLeaves() {
        return repo.doctorWithMostSickLeaves()
                .stream()
                .map(r -> r[0] + " " + r[1] + " - " + r[2] + " sick leaves")
                .toList();
    }
}
