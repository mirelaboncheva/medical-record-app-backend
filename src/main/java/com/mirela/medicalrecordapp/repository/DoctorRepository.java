package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
