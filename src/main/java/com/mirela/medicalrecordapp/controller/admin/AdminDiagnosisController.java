package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDiagnosisRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DiagnosisDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDiagnosisRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminDiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/diagnoses")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDiagnosisController {

    private final AdminDiagnosisService service;

    @GetMapping
    public List<DiagnosisDto> listAll() {
        return service.getAllDiagnoses();
    }

    @GetMapping("/{id}")
    public DiagnosisDto getById(@PathVariable Long id) {
        return service.getDiagnosisById(id);
    }

    @PostMapping
    public DiagnosisDto create(@RequestBody @Valid CreateDiagnosisRequestDto dto) {
        return service.createDiagnosis(dto);
    }

    @PutMapping("/{id}")
    public DiagnosisDto update(@PathVariable Long id,
                               @RequestBody @Valid UpdateDiagnosisRequestDto dto) {
        return service.updateDiagnosis(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteDiagnosis(id);
    }
}