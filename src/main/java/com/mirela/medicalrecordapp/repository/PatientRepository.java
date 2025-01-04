package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
