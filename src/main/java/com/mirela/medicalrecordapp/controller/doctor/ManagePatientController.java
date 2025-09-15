package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.PatientListDto;
import com.mirela.medicalrecordapp.service.doctor.DoctorPatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/patients")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class ManagePatientController {

    private final DoctorPatientService service;

    @GetMapping
    public List<PatientListDto> listAllPatients() {
        return service.getAllPatients();
    }
}