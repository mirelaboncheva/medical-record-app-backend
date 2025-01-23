package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDiagnosisById(@PathVariable Long id) {
        diagnosisService.deleteDiagnosis(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
