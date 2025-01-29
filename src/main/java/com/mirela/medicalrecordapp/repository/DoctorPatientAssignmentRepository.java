package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorPatientAssignmentRepository extends JpaRepository<DoctorPatientAssignment, Long> {
    List<DoctorPatientAssignment> findByDoctor_Id(Long doctorId);
    @Query("SELECT d.doctor, COUNT(d.patient) FROM DoctorPatientAssignment d GROUP BY d.doctor")
    List<Object[]> countPatientsPerDoctor();
}
