package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateSickLeaveRequestDto;
import com.mirela.medicalrecordapp.dto.admin.SickLeaveDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateSickLeaveRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminSickLeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/sick-leaves")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminSickLeaveController {

    private final AdminSickLeaveService service;

    @GetMapping
    public List<SickLeaveDto> listAll() {
        return service.getAllSickLeaves();
    }

    @GetMapping("/{id}")
    public SickLeaveDto getById(@PathVariable Long id) {
        return service.getSickLeaveById(id);
    }

    @PostMapping
    public SickLeaveDto create(@RequestBody @Valid CreateSickLeaveRequestDto dto) {
        return service.createSickLeave(dto);
    }

    @PutMapping("/{id}")
    public SickLeaveDto update(@PathVariable Long id,
                               @RequestBody @Valid UpdateSickLeaveRequestDto dto) {
        return service.updateSickLeave(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteSickLeave(id);
    }
}