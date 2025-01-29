package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.model.Treatment;

import java.util.List;

public interface TreatmentService {
    List<TreatmentDTO> getTreatments();
    TreatmentDTO getTreatmentById(Long id);
    Treatment saveTreatment(TreatmentDTO treatmentDTO);
    Treatment updateTreatment(Long id, TreatmentDTO treatmentDTO);
    void deleteTreatment(Long id);
}
