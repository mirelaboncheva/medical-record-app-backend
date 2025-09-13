package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreatePatientRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.dto.admin.UpdatePatientRequestDto;
import com.mirela.medicalrecordapp.service.admin.impl.AdminPatientServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{id}")
    public ManagePatientDto getPatient(@PathVariable Long id) {
        return adminPatientService.getPatientById(id);
    }

    @PostMapping
    public ManagePatientDto createPatient(@RequestBody @Valid CreatePatientRequestDto dto) {
        return adminPatientService.createPatient(dto);
    }

    @PutMapping("/{id}")
    public ManagePatientDto updatePatient(@PathVariable Long id,
                                          @RequestBody @Valid UpdatePatientRequestDto dto) {
        return adminPatientService.updatePatient(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        adminPatientService.deletePatient(id);
    }
}