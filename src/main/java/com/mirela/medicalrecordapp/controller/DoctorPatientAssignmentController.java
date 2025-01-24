package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.service.DoctorPatientAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gp-assignments")
public class DoctorPatientAssignmentController {

    private final DoctorPatientAssignmentService doctorPatientAssignmentService;

    @GetMapping
    public ResponseEntity<List<DoctorPatientAssignmentResponse>> getGPAssignments() {
        return ResponseEntity.ok(doctorPatientAssignmentService.getGPAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorPatientAssignmentResponse> getGPAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorPatientAssignmentService.getGPAssignmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGPAssignmentById(@PathVariable Long id) {
        doctorPatientAssignmentService.deleteGPAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
