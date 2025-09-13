package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.dto.admin.CreateAppointmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateAppointmentRequestDto;

import java.util.List;

public interface AdminAppointmentService {

    List<AppointmentDto> getAllAppointments();
    AppointmentDto getAppointmentById(Long id);
    AppointmentDto createAppointment(CreateAppointmentRequestDto dto);
    AppointmentDto updateAppointment(Long id, UpdateAppointmentRequestDto dto);
    void deleteAppointment(Long id);
}
