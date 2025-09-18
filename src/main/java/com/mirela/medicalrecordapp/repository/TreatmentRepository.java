package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Treatment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

    @EntityGraph(attributePaths = {
            "appointment.patient.user",
            "appointment.doctor.user"
    })
    List<Treatment> findAll();

    @EntityGraph(attributePaths = {
            "appointment.doctor.user",
            "appointment.patient"
    })
    List<Treatment> findByAppointmentPatientIdOrderByAppointmentAppointmentDateDesc(Long patientId);
}
