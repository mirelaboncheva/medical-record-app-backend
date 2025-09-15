package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateTreatmentRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateTreatmentRequest;
import com.mirela.medicalrecordapp.model.Treatment;

public interface ManageTreatmentService {

    Treatment addTreatment(Long appointmentId, CreateTreatmentRequest request);
    Treatment updateTreatment(Long treatmentId, UpdateTreatmentRequest request);
    void deleteTreatment(Long treatmentId);

}
