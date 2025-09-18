package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorPatientAssignmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.DoctorPatientAssignmentResponseDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorPatientAssignmentRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorPatientAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/assignments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminDoctorPatientAssignmentController {

    private final AdminDoctorPatientAssignmentService service;

    @GetMapping
    public List<DoctorPatientAssignmentResponseDto> listAll() {
        return service.getAllAssignments();
    }

    @PostMapping
    public DoctorPatientAssignmentResponseDto create(@RequestBody @Valid CreateDoctorPatientAssignmentRequestDto dto) {
        return service.createAssignment(dto);
    }

    @PutMapping("/{id}")
    public DoctorPatientAssignmentResponseDto update(@PathVariable Long id,
                                             @RequestBody @Valid UpdateDoctorPatientAssignmentRequestDto dto) {
        return service.updateAssignment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAssignment(id);
    }
}
