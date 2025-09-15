package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.DoctorProfileDto;
import com.mirela.medicalrecordapp.mapper.DoctorMapper;
import com.mirela.medicalrecordapp.model.User;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.service.doctor.DoctorProfileService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorProfileServiceImpl implements DoctorProfileService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper mapper;

    @PreAuthorize("hasRole('DOCTOR')")
    public DoctorProfileDto getMyProfile() {
        Long userId = getCurrentUserId();
        var doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found"));

        DoctorProfileDto dto = mapper.toDto(doctor);
        dto.setDoctorUid(doctor.getDoctorUid());
        dto.setSpecialization(doctor.getSpecialization());
        dto.setNumberOfRegisteredPatients(doctorRepository.countActivePatients(doctor.getId()));

        return dto;
    }

    private Long getCurrentUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ((User) auth.getPrincipal()).getId();
    }
}
