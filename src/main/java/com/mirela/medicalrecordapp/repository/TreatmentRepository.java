package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
