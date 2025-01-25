package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DiagnosisRequest;
import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.dto.UserRequest;
import com.mirela.medicalrecordapp.dto.UserUpdateRequest;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.service.DiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/diagnoses")
public class DiagnosisController {

    private final DiagnosisService diagnosisService;

    @GetMapping
    public ResponseEntity<List<DiagnosisResponse>> getDiagnoses() {
        return ResponseEntity.ok(diagnosisService.getDiagnoses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiagnosisResponse> getDiagnosisById(@PathVariable Long id) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<DiagnosisResponse> getDiagnosisByName(@PathVariable String name) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisByName(name));
    }

    @PostMapping
    public ResponseEntity<Diagnosis> saveDiagnosis(@RequestBody DiagnosisRequest diagnosisRequest) {
        Diagnosis savedDiagnosis = diagnosisService.saveDiagnosis(diagnosisRequest);
        return new ResponseEntity<>(savedDiagnosis, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diagnosis> updateDiagnosis(@PathVariable Long id, @RequestBody DiagnosisRequest diagnosisRequest) {
        return ResponseEntity.ok(diagnosisService.updateDiagnosis(id, diagnosisRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosisById(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
