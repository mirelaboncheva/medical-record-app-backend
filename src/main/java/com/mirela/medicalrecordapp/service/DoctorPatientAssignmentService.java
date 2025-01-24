package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.DoctorPatientCountResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;

import java.util.List;

public interface DoctorPatientAssignmentService {
    List<DoctorPatientAssignmentResponse> getDoctorPatientAssignments();
    DoctorPatientAssignmentResponse getDoctorPatientAssignmentById(Long id);
    List<PatientResponse> getPatientsByDoctorId(Long doctorId);
    List<DoctorPatientCountResponse> getDoctorPatientCounts();
    //    DoctorPatientAssignment saveGPAssignment(GPRequest gpRequest);
    //    DoctorPatientAssignment updateGPAssignment(Long id, GPUpdateRequest gpUpdateRequest);
    void deleteDoctorPatientAssignment(Long id);
}
