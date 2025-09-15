package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.*;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.service.doctor.MyAppointmentsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/api/doctor/appointments")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class MyAppointmentsController {

    private final MyAppointmentsService service;

    @GetMapping
    public List<MyAppointmentSummaryDto> listMyAppointments() {
        return service.getMyAppointments();
    }

    @GetMapping("/{id}")
    public MyAppointmentDetailsDto getAppointmentDetails(@PathVariable Long id) {
        return service.getMyAppointmentDetails(id);
    }

    @PostMapping
    public Appointment createAppointment(@Valid @RequestBody CreateAppointmentRequest request) {
        return service.createAppointment(request);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id,
                                         @Valid @RequestBody UpdateAppointmentRequest request) {
        return service.updateAppointment(id, request);
    }

    @PatchMapping("/{id}/status")
    public Appointment changeStatus(@PathVariable Long id,
                                    @Valid @RequestBody ChangeAppointmentStatusRequest request) {
        return service.changeStatus(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        service.deleteAppointment(id);
    }
}
