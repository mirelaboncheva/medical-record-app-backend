package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.service.admin.impl.AdminPatientServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/patients")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminPatientController {

    private final AdminPatientServiceImpl adminPatientService;

    @GetMapping
    public List<ManagePatientDto> listAllPatients() {
        return adminPatientService.getAllPatients();
    }
}