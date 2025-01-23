package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {
}
