package com.mirela.medicalrecordapp.dto.doctor;

import com.mirela.medicalrecordapp.model.enums.Status;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangeAppointmentStatusRequest {

    @NotBlank(message = "Status is required")
    private Status status;
}
