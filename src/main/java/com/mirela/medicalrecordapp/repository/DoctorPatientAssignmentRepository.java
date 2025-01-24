package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorPatientAssignmentRepository extends JpaRepository<DoctorPatientAssignment, Long> {
}
