package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.SickLeaveResponse;
import com.mirela.medicalrecordapp.dto.TreatmentResponse;

import java.util.List;

public interface SickLeaveService {
    List<SickLeaveResponse> getSickLeaves();
    SickLeaveResponse getSickLeaveById(Long id);
    void deleteSickLeave(Long id);
}
