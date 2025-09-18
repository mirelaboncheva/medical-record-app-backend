package com.mirela.medicalrecordapp.controller.patient;

import com.mirela.medicalrecordapp.dto.patient.MyTreatmentDto;
import com.mirela.medicalrecordapp.service.patient.MyTreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/treatments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PATIENT')")
public class MyTreatmentController {

    private final MyTreatmentService service;

    @GetMapping
    public List<MyTreatmentDto> listMyTreatments() {
        return service.getMyTreatments();
    }
}