package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDiagnosisRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DiagnosisDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDiagnosisRequestDto;

import java.util.List;

public interface AdminDiagnosisService {

    List<DiagnosisDto> getAllDiagnoses();
    DiagnosisDto getDiagnosisById(Long id);
    DiagnosisDto createDiagnosis(CreateDiagnosisRequestDto dto);
    DiagnosisDto updateDiagnosis(Long id, UpdateDiagnosisRequestDto dto);
    void deleteDiagnosis(Long id);

}
