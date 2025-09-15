package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateTreatmentRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateTreatmentRequest;
import com.mirela.medicalrecordapp.model.Treatment;
import com.mirela.medicalrecordapp.service.doctor.ManageTreatmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class ManageTreatmentController {

    private final ManageTreatmentService treatmentService;

    @PostMapping("/appointments/{appointmentId}/treatments")
    public Treatment addTreatment(@PathVariable Long appointmentId,
                                  @Valid @RequestBody CreateTreatmentRequest request) {
        return treatmentService.addTreatment(appointmentId, request);
    }

    @PutMapping("/treatments/{id}")
    public Treatment updateTreatment(@PathVariable Long id,
                                     @Valid @RequestBody UpdateTreatmentRequest request) {
        return treatmentService.updateTreatment(id, request);
    }

    @DeleteMapping("/treatments/{id}")
    public void deleteTreatment(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
    }
}
