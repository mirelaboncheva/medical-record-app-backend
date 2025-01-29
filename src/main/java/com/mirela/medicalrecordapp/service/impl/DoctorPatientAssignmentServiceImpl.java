package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.AssignPatientDoctorRequest;
import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.DoctorPatientCountResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.mapper.DoctorPatientAssignmentMapper;
import com.mirela.medicalrecordapp.mapper.UserMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.DoctorPatientAssignmentRepository;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.DoctorPatientAssignmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorPatientAssignmentServiceImpl implements DoctorPatientAssignmentService {

    private final DoctorPatientAssignmentRepository doctorPatientAssignmentRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorPatientAssignmentMapper doctorPatientAssignmentMapper;
    private final UserMapper userMapper;


    @Override
    public List<DoctorPatientAssignmentResponse> getDoctorPatientAssignments() {
        List<DoctorPatientAssignment> doctorPatientAssignments = doctorPatientAssignmentRepository.findAll();
        return doctorPatientAssignmentMapper.toDTOList(doctorPatientAssignments);
    }

    @Override
    public DoctorPatientAssignmentResponse getDoctorPatientAssignmentById(Long id) {
        return doctorPatientAssignmentRepository.findById(id)
                .map(doctorPatientAssignmentMapper::toDTO)
                .orElse(null); //TODO
    }

    @Override
    public List<PatientResponse> getPatientsByDoctorId(Long doctorId) {
        List<DoctorPatientAssignment> assignments = doctorPatientAssignmentRepository.findByDoctor_Id(doctorId);
        return assignments.stream()
                .map(DoctorPatientAssignmentMapper.INSTANCE::toDTO)
                .map(DoctorPatientAssignmentResponse::getPatientResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<DoctorPatientCountResponse> getDoctorPatientCounts() {
        List<Object[]> results = doctorPatientAssignmentRepository.countPatientsPerDoctor();
        return results.stream()
                .map(result -> DoctorPatientCountResponse.builder()
                        .doctorResponse(userMapper.toDoctorResponse((Doctor) result[0]))
                        .patientCount((Long) result[1])
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public DoctorPatientAssignment saveAssignment(AssignPatientDoctorRequest assignmentRequest) {
        Patient patient = patientRepository.findById(assignmentRequest.getPatientAssignRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + assignmentRequest.getPatientAssignRequest().getId()));

        Doctor doctor = doctorRepository.findById(assignmentRequest.getDoctorAssignRequest().getId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + assignmentRequest.getDoctorAssignRequest().getId()));

        if (doctorPatientAssignmentRepository.findById(patient.getId()).isPresent()) {
            throw new IllegalStateException("Patient is already assigned to a GP.");
        }

        var assignment = DoctorPatientAssignment.builder()
                .patient(patient)
                .doctor(doctor)
                .registrationDate(LocalDate.now())
                .deregistrationDate(LocalDate.now().plusYears(1))
                .build();

        return doctorPatientAssignmentRepository.save(assignment);
    }

    @Override
    public void deleteDoctorPatientAssignment(Long id) {
        doctorPatientAssignmentRepository.deleteById(id);
    }
}
