package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Doctor;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @EntityGraph(attributePaths = {
            "user",
            "personalPatients.doctor.user"
    })
    List<Doctor> findAll();

    @EntityGraph(attributePaths = {"user", "personalPatients"})
    Optional<Doctor> findByUserId(Long userId);

    @Query("SELECT COUNT(dpa) FROM DoctorPatientAssignment dpa WHERE dpa.doctor.id = :doctorId AND dpa.deregistrationDate IS NULL")
    Long countActivePatients(Long doctorId);

    @Transactional
    void deleteByUserId(Long userId);

}
