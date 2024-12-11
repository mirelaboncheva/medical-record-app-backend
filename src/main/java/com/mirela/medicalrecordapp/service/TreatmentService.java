package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.PatientDTO;
import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.model.Treatment;

import java.util.List;

public interface TreatmentService {
    List<TreatmentDTO> getTreatments();
    TreatmentDTO getTreatmentById(Long id);
    Treatment saveTreatment(TreatmentDTO treatmentDTO);
    //TreatmentDTO updateTreatment(TreatmentDTO treatmentDTO, Long id);
    void deleteTreatment(Long id);
}
