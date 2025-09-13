package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.mapper.DoctorMapper;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.service.admin.AdminDoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDoctorServiceImpl implements AdminDoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<ManageDoctorDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doctorMapper::toManageDto)
                .toList();
    }
}
