package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateDiagnosisRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateDiagnosisRequest;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.service.doctor.ManageDiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class ManageDiagnosisController {

    private final ManageDiagnosisService diagnosisService;

    @PostMapping("/appointments/{appointmentId}/diagnoses")
    public Diagnosis addDiagnosis(@PathVariable Long appointmentId,
                                  @Valid @RequestBody CreateDiagnosisRequest request) {
        return diagnosisService.addDiagnosis(appointmentId, request);
    }

    @PutMapping("/diagnoses/{id}")
    public Diagnosis updateDiagnosis(@PathVariable Long id,
                                     @Valid @RequestBody UpdateDiagnosisRequest request) {
        return diagnosisService.updateDiagnosis(id, request);
    }

    @DeleteMapping("/diagnoses/{id}")
    public void deleteDiagnosis(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
    }
}
