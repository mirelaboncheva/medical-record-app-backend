package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.mapper.DoctorPatientAssignmentMapper;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.repository.DoctorPatientAssignmentRepository;
import com.mirela.medicalrecordapp.service.DoctorPatientAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorPatientAssignmentServiceImpl implements DoctorPatientAssignmentService {

    private final DoctorPatientAssignmentRepository doctorPatientAssignmentRepository;
    private final DoctorPatientAssignmentMapper doctorPatientAssignmentMapper;


    @Override
    public List<DoctorPatientAssignmentResponse> getGPAssignments() {
        List<DoctorPatientAssignment> doctorPatientAssignments = doctorPatientAssignmentRepository.findAll();
        return doctorPatientAssignmentMapper.toDTOList(doctorPatientAssignments);
    }

    @Override
    public DoctorPatientAssignmentResponse getGPAssignmentById(Long id) {
        return doctorPatientAssignmentRepository.findById(id)
                .map(doctorPatientAssignmentMapper::toDTO)
                .orElse(null); //TODO
    }

    @Override
    public PatientResponse getPatientsByDoctorId(Long doctorId) {
        return null;
    }

    @Override
    public void deleteGPAssignment(Long id) {
        doctorPatientAssignmentRepository.deleteById(id);
    }
}
