package com.mirela.medicalrecordapp.controller.patient;

import com.mirela.medicalrecordapp.dto.patient.PatientProfileDto;
import com.mirela.medicalrecordapp.service.patient.PatientProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient/profile")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PATIENT')")
public class PatientProfileController {

    private final PatientProfileService service;

    @GetMapping
    public PatientProfileDto getMyProfile() {
        return service.getMyProfile();
    }
}