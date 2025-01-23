package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.GPResponse;
import com.mirela.medicalrecordapp.service.GPAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gp-assignments")
public class GPAssignmentController {

    private final GPAssignmentService gpAssignmentService;

    @GetMapping
    public ResponseEntity<List<GPResponse>> getGPAssignments() {
        return ResponseEntity.ok(gpAssignmentService.getGPAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GPResponse> getGPAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(gpAssignmentService.getGPAssignmentById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGPAssignmentById(@PathVariable Long id) {
        gpAssignmentService.deleteGPAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
