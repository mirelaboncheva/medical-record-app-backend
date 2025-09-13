package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "personalPatients.doctor.user"
    })
    List<Doctor> findAll();

    @Transactional
    void deleteByUserId(Long userId);
}
