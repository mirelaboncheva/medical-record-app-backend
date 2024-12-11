package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.GeneralPractitioner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneralPractitionerRepository extends JpaRepository<GeneralPractitioner, Long> {
}
