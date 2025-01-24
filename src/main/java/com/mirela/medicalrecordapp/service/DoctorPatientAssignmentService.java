package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;

import java.util.List;

public interface DoctorPatientAssignmentService {
    List<DoctorPatientAssignmentResponse> getGPAssignments();
    DoctorPatientAssignmentResponse getGPAssignmentById(Long id);
    PatientResponse getPatientsByDoctorId(Long doctorId);
//    DoctorPatientAssignment saveGPAssignment(GPRequest gpRequest);
//    DoctorPatientAssignment updateGPAssignment(Long id, GPUpdateRequest gpUpdateRequest);
    void deleteGPAssignment(Long id);
}
