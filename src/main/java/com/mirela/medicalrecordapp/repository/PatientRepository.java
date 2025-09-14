package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "DoctorPatientAssignment.doctor.user"
    })
    List<Patient> findAll();

    @Transactional
    void deleteByUserId(Long userId);

    Optional<Patient> findById(Long id);

    @EntityGraph(attributePaths = {
            "user",
            "DoctorPatientAssignment.doctor.user"
    })
    Optional<Patient> findByUserId(Long userId);

}
