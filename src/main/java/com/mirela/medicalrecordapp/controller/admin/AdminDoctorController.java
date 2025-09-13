package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/doctors")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDoctorController {

    private final AdminDoctorService adminDoctorService;

    @GetMapping
    public List<ManageDoctorDto> listAllDoctors() {
        return adminDoctorService.getAllDoctors();
    }
}