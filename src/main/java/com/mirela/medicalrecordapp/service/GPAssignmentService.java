package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.GPResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;

import java.util.List;

public interface GPAssignmentService {
    List<GPResponse> getGPAssignments();
    GPResponse getGPAssignmentById(Long id);
    PatientResponse getPatientsByDoctorId(Long doctorId);
//    GPAssignment saveGPAssignment(GPRequest gpRequest);
//    GPAssignment updateGPAssignment(Long id, GPUpdateRequest gpUpdateRequest);
    void deleteGPAssignment(Long id);
}
