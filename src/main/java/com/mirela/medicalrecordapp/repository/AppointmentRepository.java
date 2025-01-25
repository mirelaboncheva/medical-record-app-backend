package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    //List<Appointment> findByDoctorIdAndAppointmentDateAndStatus(Long doctorId, LocalDate date, String status);
}
