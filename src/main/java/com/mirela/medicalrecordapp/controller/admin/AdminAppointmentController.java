package com.mirela.medicalrecordapp.controller.admin;

import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.dto.admin.CreateAppointmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateAppointmentRequestDto;
import com.mirela.medicalrecordapp.service.admin.AdminAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/appointments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class AdminAppointmentController {

    private final AdminAppointmentService service;

    @GetMapping
    public List<AppointmentDto> listAll() {
        return service.getAllAppointments();
    }

    @GetMapping("/{id}")
    public AppointmentDto getById(@PathVariable Long id) {
        return service.getAppointmentById(id);
    }

    @PostMapping
    public AppointmentDto create(@RequestBody @Valid CreateAppointmentRequestDto dto) {
        return service.createAppointment(dto);
    }

    @PutMapping("/{id}")
    public AppointmentDto update(@PathVariable Long id,
                                 @RequestBody @Valid UpdateAppointmentRequestDto dto) {
        return service.updateAppointment(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}