package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.*;
import com.mirela.medicalrecordapp.model.Appointment;

import java.util.List;

public interface MyAppointmentsService {

    List<MyAppointmentSummaryDto> getMyAppointments();
    MyAppointmentDetailsDto getMyAppointmentDetails(Long appointmentId);
    Appointment createAppointment(CreateAppointmentRequest request);
    Appointment updateAppointment(Long appointmentId, UpdateAppointmentRequest request);
    Appointment changeStatus(Long appointmentId, ChangeAppointmentStatusRequest request);
    void deleteAppointment(Long appointmentId);

}
