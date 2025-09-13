package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreatePatientRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.dto.admin.UpdatePatientRequestDto;
import com.mirela.medicalrecordapp.mapper.PatientMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.model.enums.Role;
import com.mirela.medicalrecordapp.repository.DoctorPatientAssignmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.repository.UserRepository;
import com.mirela.medicalrecordapp.service.admin.AdminPatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminPatientServiceImpl implements AdminPatientService {

    private final PatientRepository patientRepository;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorPatientAssignmentRepository assignmentRepository;
    private final PatientMapper patientMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<ManagePatientDto> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(patientMapper::toManageDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ManagePatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        return patientMapper.toManageDto(patient);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ManagePatientDto createPatient(CreatePatientRequestDto dto) {
        // Create User
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(Role.PATIENT);
        // TODO: set password (generate or accept from dto)
        userRepository.save(user);

        // Create Patient
        Patient patient = new Patient();
        patient.setNationalId(dto.getNationalId());
        patient.setIsHealthInsurancePaid(dto.isHealthInsurancePaid());
        patient.setUser(user);
        patientRepository.save(patient);

        // Assign personal doctor if provided
        if (dto.getPersonalDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getPersonalDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            DoctorPatientAssignment assignment = new DoctorPatientAssignment();
            assignment.setPatient(patient);
            assignment.setDoctor(doctor);
            assignment.setRegistrationDate(LocalDate.now());
            assignmentRepository.save(assignment);
            patient.setDoctorPatientAssignment(assignment);
        }

        return patientMapper.toManageDto(patient);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ManagePatientDto updatePatient(Long id, UpdatePatientRequestDto dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));

        // Update linked User
        User user = patient.getUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());

        // Update patient fields
        patient.setIsHealthInsurancePaid(dto.isHealthInsurancePaid());

        // Update doctor assignment if provided
        if (dto.getPersonalDoctorId() != null) {
            Doctor doctor = doctorRepository.findById(dto.getPersonalDoctorId())
                    .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
            DoctorPatientAssignment assignment = patient.getDoctorPatientAssignment();
            if (assignment == null) {
                assignment = new DoctorPatientAssignment();
                assignment.setPatient(patient);
            }
            assignment.setDoctor(doctor);
            assignment.setRegistrationDate(LocalDate.now());
            assignmentRepository.save(assignment);
            patient.setDoctorPatientAssignment(assignment);
        }

        return patientMapper.toManageDto(patient);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found"));
        patientRepository.delete(patient);
    }
}