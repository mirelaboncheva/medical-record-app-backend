package com.mirela.medicalrecordapp.controller.patient;

import com.mirela.medicalrecordapp.dto.patient.MySickLeaveDto;
import com.mirela.medicalrecordapp.service.patient.MySickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/sick-leaves")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PATIENT')")
public class MySickLeaveController {

    private final MySickLeaveService service;

    @GetMapping
    public List<MySickLeaveDto> listMySickLeaves() {
        return service.getMySickLeaves();
    }
}