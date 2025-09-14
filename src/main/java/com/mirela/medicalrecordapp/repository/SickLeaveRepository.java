package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.SickLeave;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {

    @EntityGraph(attributePaths = {"appointment.patient.user", "appointment.doctor.user"})
    List<SickLeave> findAll();
}
