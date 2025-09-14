package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Diagnosis;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
    Optional<Diagnosis> findByName(String name);

    @EntityGraph(attributePaths = {"appointment.patient.user", "appointment.doctor.user"})
    List<Diagnosis> findAll();
}
