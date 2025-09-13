package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorPatientAssignmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DoctorPatientAssignmentResponseDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorPatientAssignmentRequestDto;
import com.mirela.medicalrecordapp.mapper.DoctorPatientAssignmentMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.DoctorPatientAssignmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorPatientAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDoctorPatientAssignmentServiceImpl implements AdminDoctorPatientAssignmentService {

    private final DoctorPatientAssignmentRepository assignmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorPatientAssignmentMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<DoctorPatientAssignmentResponseDto> getAllAssignments() {
        return assignmentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DoctorPatientAssignmentResponseDto createAssignment(CreateDoctorPatientAssignmentRequestDto dto) {
        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        DoctorPatientAssignment assignment = new DoctorPatientAssignment();
        assignment.setPatient(patient);
        assignment.setDoctor(doctor);
        assignment.setRegistrationDate(dto.getRegistrationDate() != null ? dto.getRegistrationDate() : LocalDate.now());

        assignmentRepository.save(assignment);
        patient.setDoctorPatientAssignment(assignment);

        return mapper.toDto(assignment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public DoctorPatientAssignmentResponseDto updateAssignment(Long id, UpdateDoctorPatientAssignmentRequestDto dto) {
        DoctorPatientAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));

        if (dto.getDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            assignment.setDoctor(doctor);
        }

        if (dto.getDeregistrationDate() != null) {
            assignment.setDeregistrationDate(dto.getDeregistrationDate());
        }

        assignmentRepository.save(assignment);
        return mapper.toDto(assignment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAssignment(Long id) {
        DoctorPatientAssignment assignment = assignmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assignment not found"));
        assignmentRepository.delete(assignment);
    }
}