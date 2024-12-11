package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.GPAssignmentDTO;
import com.mirela.medicalrecordapp.model.GeneralPractitioner;
import com.mirela.medicalrecordapp.service.GeneralPractitionerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gp-assignments")
public class GeneralPractitionerController {

    private final GeneralPractitionerService generalPractitionerService;

    @PostMapping
    public ResponseEntity<GeneralPractitioner> createGpAssignment(@RequestBody GPAssignmentDTO assignmentDTO) {
        GeneralPractitioner savedGp = generalPractitionerService.saveGeneralPractitioner(assignmentDTO);
        return new ResponseEntity<>(savedGp, HttpStatus.CREATED);
    }
}
