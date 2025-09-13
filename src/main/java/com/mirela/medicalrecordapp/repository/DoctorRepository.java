package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Transactional
    void deleteByUserId(Long userId);
}
