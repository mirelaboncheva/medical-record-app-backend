package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorRequestDto;
import com.mirela.medicalrecordapp.mapper.DoctorMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.model.enums.Role;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.UserRepository;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorService;
import com.mirela.medicalrecordapp.util.PasswordGenerator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final DoctorMapper doctorMapper;
    private final PasswordEncoder passwordEncoder;

    @PreAuthorize("hasRole('ADMIN')")
    public List<ManageDoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toManageDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ManageDoctorDto getDoctorById(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));
        return doctorMapper.toManageDto(doctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public ManageDoctorDto createDoctor(CreateDoctorRequestDto dto) {
        // Generate password
        String rawPassword = PasswordGenerator.generateSimplePassword();
        String hashedPassword = passwordEncoder.encode(rawPassword);

        // Create User
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(Role.DOCTOR);
        user.setPassword(hashedPassword);
        userRepository.save(user);

        // Create Doctor
        Doctor doctor = new Doctor();
        doctor.setDoctorUid(UUID.randomUUID().toString());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setUser(user);
        doctorRepository.save(doctor);

        System.out.println("Generated password for doctor: " + rawPassword);

        return doctorMapper.toManageDto(doctor);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ManageDoctorDto updateDoctor(Long id, UpdateDoctorRequestDto dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        User user = doctor.getUser();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPhoneNumber(dto.getPhoneNumber());

        doctor.setSpecialization(dto.getSpecialization());

        return doctorMapper.toManageDto(doctor);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        // Remove only the doctor profile, keep User if they have other roles
        doctorRepository.delete(doctor);
    }
}
