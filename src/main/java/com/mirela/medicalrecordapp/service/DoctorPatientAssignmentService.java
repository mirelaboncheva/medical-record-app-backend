package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.AssignPatientDoctorRequest;
import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.DoctorPatientCountResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;

import java.util.List;

public interface DoctorPatientAssignmentService {
    List<DoctorPatientAssignmentResponse> getDoctorPatientAssignments();
    DoctorPatientAssignmentResponse getDoctorPatientAssignmentById(Long id);
    List<PatientResponse> getPatientsByDoctorId(Long doctorId);
    List<DoctorPatientCountResponse> getDoctorPatientCounts();
    DoctorPatientAssignment saveAssignment(AssignPatientDoctorRequest assignmentRequest);
    //    DoctorPatientAssignment updateGPAssignment(Long id, GPUpdateRequest gpUpdateRequest);
    void deleteDoctorPatientAssignment(Long id);
}
