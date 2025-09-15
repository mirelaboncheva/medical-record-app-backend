package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateSickLeaveRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateSickLeaveRequest;
import com.mirela.medicalrecordapp.model.SickLeave;

public interface ManageSickLeaveService {

    SickLeave issueSickLeave(Long appointmentId, CreateSickLeaveRequest request);
    SickLeave updateSickLeave(Long sickLeaveId, UpdateSickLeaveRequest request);
    void deleteSickLeave(Long sickLeaveId);
}
