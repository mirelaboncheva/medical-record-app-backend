package com.mirela.medicalrecordapp.controller.patient;

import com.mirela.medicalrecordapp.dto.patient.MyDiagnosisDto;
import com.mirela.medicalrecordapp.service.patient.MyDiagnosisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/diagnoses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PATIENT')")
public class MyDiagnosisController {

    private final MyDiagnosisService service;

    @GetMapping
    public List<MyDiagnosisDto> listMyDiagnoses() {
        return service.getMyDiagnoses();
    }
}