package com.mirela.medicalrecordapp.controller.patient;

import com.mirela.medicalrecordapp.dto.patient.MyVisitDetailsDto;
import com.mirela.medicalrecordapp.dto.patient.MyVisitSummaryDto;
import com.mirela.medicalrecordapp.service.patient.MyVisitsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patient/visits")
@RequiredArgsConstructor
@PreAuthorize("hasRole('PATIENT')")
public class MyVisitsController {

    private final MyVisitsService service;

    @GetMapping
    public List<MyVisitSummaryDto> listMyVisits() {
        return service.getMyVisits();
    }

    @GetMapping("/{id}")
    public MyVisitDetailsDto getVisitDetails(@PathVariable Long id) {
        return service.getMyVisitDetails(id);
    }
}
