package com.mirela.medicalrecordapp.controller.doctor;

import com.mirela.medicalrecordapp.dto.doctor.CreateSickLeaveRequest;
import com.mirela.medicalrecordapp.dto.doctor.UpdateSickLeaveRequest;
import com.mirela.medicalrecordapp.model.SickLeave;
import com.mirela.medicalrecordapp.service.doctor.ManageSickLeaveService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@PreAuthorize("hasRole('DOCTOR')")
public class ManageSickLeaveController {

    private final ManageSickLeaveService sickLeaveService;

    @PostMapping("/appointments/{appointmentId}/sick-leave")
    public SickLeave issueSickLeave(@PathVariable Long appointmentId,
                                    @Valid @RequestBody CreateSickLeaveRequest request) {
        return sickLeaveService.issueSickLeave(appointmentId, request);
    }

    @PutMapping("/sick-leaves/{id}")
    public SickLeave updateSickLeave(@PathVariable Long id,
                                     @Valid @RequestBody UpdateSickLeaveRequest request) {
        return sickLeaveService.updateSickLeave(id, request);
    }

    @DeleteMapping("/sick-leaves/{id}")
    public void deleteSickLeave(@PathVariable Long id) {
        sickLeaveService.deleteSickLeave(id);
    }
}
