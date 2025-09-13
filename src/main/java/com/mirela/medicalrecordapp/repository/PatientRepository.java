package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Transactional
    void deleteByUserId(Long userId);
}
