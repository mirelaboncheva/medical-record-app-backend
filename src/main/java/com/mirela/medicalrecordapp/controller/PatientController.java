package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.HealthInsuranceUpdateRequest;
import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.model.Patient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.mirela.medicalrecordapp.service.PatientService;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @GetMapping
    public ResponseEntity<List<PatientPersonalDataResponse>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientPersonalDataResponse> getPatientById(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PutMapping("/{id}/health-insurance")
    public ResponseEntity<Patient> updateHealthInsurance(
            @PathVariable Long id,
            @RequestBody HealthInsuranceUpdateRequest request) {
        Patient updatedPatient = patientService.updateHealthInsurance(id, request);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
