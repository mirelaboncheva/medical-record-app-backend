package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorPatientAssignmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DoctorPatientAssignmentResponseDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorPatientAssignmentRequestDto;

import java.util.List;

public interface AdminDoctorPatientAssignmentService {

    List<DoctorPatientAssignmentResponseDto> getAllAssignments();
    DoctorPatientAssignmentResponseDto createAssignment(CreateDoctorPatientAssignmentRequestDto dto);
    DoctorPatientAssignmentResponseDto updateAssignment(Long id, UpdateDoctorPatientAssignmentRequestDto dto);
    void deleteAssignment(Long id);
}
