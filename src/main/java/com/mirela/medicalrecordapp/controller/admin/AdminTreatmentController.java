package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateTreatmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.TreatmentDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateTreatmentRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminTreatmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/treatments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminTreatmentController {

    private final AdminTreatmentService service;

    @GetMapping
    public List<TreatmentDto> listAll() {
        return service.getAllTreatments();
    }

    @GetMapping("/{id}")
    public TreatmentDto getById(@PathVariable Long id) {
        return service.getTreatmentById(id);
    }

    @PostMapping
    public TreatmentDto create(@RequestBody @Valid CreateTreatmentRequestDto dto) {
        return service.createTreatment(dto);
    }

    @PutMapping("/{id}")
    public TreatmentDto update(@PathVariable Long id,
                               @RequestBody @Valid UpdateTreatmentRequestDto dto) {
        return service.updateTreatment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteTreatment(id);
    }
}