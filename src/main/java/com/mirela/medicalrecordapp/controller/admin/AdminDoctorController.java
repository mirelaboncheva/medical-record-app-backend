package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ManageDoctorDto getDoctor(@PathVariable Long id) {
        return adminDoctorService.getDoctorById(id);
    }

    @PostMapping
    public ManageDoctorDto createDoctor(@RequestBody @Valid CreateDoctorRequestDto dto) {
        return adminDoctorService.createDoctor(dto);
    }

    @PutMapping("/{id}")
    public ManageDoctorDto updateDoctor(@PathVariable Long id,
                                        @RequestBody @Valid UpdateDoctorRequestDto dto) {
        return adminDoctorService.updateDoctor(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        adminDoctorService.deleteDoctor(id);
    }
}