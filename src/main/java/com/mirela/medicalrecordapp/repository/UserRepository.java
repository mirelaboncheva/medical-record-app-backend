package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    boolean existsByRole(Role role);
}
