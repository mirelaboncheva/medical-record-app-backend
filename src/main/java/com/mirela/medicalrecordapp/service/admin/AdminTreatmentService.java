package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateTreatmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.TreatmentDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateTreatmentRequestDto;

import java.util.List;

public interface AdminTreatmentService {

    List<TreatmentDto> getAllTreatments();
    TreatmentDto getTreatmentById(Long id);
    TreatmentDto createTreatment(CreateTreatmentRequestDto dto);
    TreatmentDto updateTreatment(Long id, UpdateTreatmentRequestDto dto);
    void deleteTreatment(Long id);
}
