package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.HealthInsuranceUpdateRequest;
import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.dto.TreatmentResponse;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/treatments")
public class TreatmentController {

    private final TreatmentService treatmentService;

    @GetMapping
    public ResponseEntity<List<TreatmentResponse>> getTreatments() {
        return ResponseEntity.ok(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentResponse> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatmentById(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
