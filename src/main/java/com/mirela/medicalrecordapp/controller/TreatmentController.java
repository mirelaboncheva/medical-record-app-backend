package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.model.Treatment;
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
    public ResponseEntity<List<TreatmentDTO>> getTreatments() {
        return ResponseEntity.ok(treatmentService.getTreatments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreatmentDTO> getTreatmentById(@PathVariable Long id) {
        return ResponseEntity.ok(treatmentService.getTreatmentById(id));
    }

    @PostMapping
    public ResponseEntity<Treatment> saveTreatment(@RequestBody TreatmentDTO treatmentDTO) {
        Treatment savedTreatment = treatmentService.saveTreatment(treatmentDTO);
        return new ResponseEntity<>(savedTreatment, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTreatmentById(@PathVariable Long id) {
        treatmentService.deleteTreatment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
