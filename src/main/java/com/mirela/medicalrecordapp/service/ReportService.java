package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DiagnosisCountDto;
import com.mirela.medicalrecordapp.dto.DoctorCountDto;
import com.mirela.medicalrecordapp.dto.PatientBasicDto;
import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.model.Appointment;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {

    List<PatientBasicDto> patientsWithDiagnosis(String diagnosisName);
    List<DiagnosisCountDto> mostCommonDiagnoses();
    List<PatientBasicDto> patientsByPersonalDoctor(Long doctorId);
    List<DoctorCountDto> patientCountPerDoctor();
    List<DoctorCountDto> visitCountPerDoctor();
    List<AppointmentDto> visitsByPatient(Long patientId);
    List<AppointmentDto> examsAllDoctorsInPeriod(LocalDate start, LocalDate end);
    List<AppointmentDto> examsByDoctorInPeriod(Long doctorId, LocalDate start, LocalDate end);
    String monthWithMostSickLeaves();
    List<String> doctorWithMostSickLeaves();
}
