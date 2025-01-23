package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.GPResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.mapper.GPAssignmentMapper;
import com.mirela.medicalrecordapp.model.GPAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.GPAssignmentRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.GPAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GPAssignmentServiceImpl implements GPAssignmentService {

    private final GPAssignmentRepository gpAssignmentRepository;
    private final GPAssignmentMapper gpAssignmentMapper;


    @Override
    public List<GPResponse> getGPAssignments() {
        List<GPAssignment> gpAssignments = gpAssignmentRepository.findAll();
        return gpAssignmentMapper.toDTOList(gpAssignments);
    }

    @Override
    public GPResponse getGPAssignmentById(Long id) {
        return gpAssignmentRepository.findById(id)
                .map(gpAssignmentMapper::toDTO)
                .orElse(null); //TODO
    }

    @Override
    public PatientResponse getPatientsByDoctorId(Long doctorId) {
        return null;
    }

    @Override
    public void deleteGPAssignment(Long id) {
        gpAssignmentRepository.deleteById(id);
    }
}
