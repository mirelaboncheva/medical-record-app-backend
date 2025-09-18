package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.DoctorListDto;
import com.mirela.medicalrecordapp.service.doctor.DoctorDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctor/doctors")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class DoctorDirectoryController {

    private final DoctorDirectoryService doctorDirectoryService;

    @GetMapping
    public List<DoctorListDto> listAllDoctors() {
        return doctorDirectoryService.getAllDoctors();
    }
}
