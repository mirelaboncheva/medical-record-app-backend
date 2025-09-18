package com.mirela.medicalrecordapp.service.doctor.impl;

import com.mirela.medicalrecordapp.dto.doctor.DoctorListDto;
import com.mirela.medicalrecordapp.mapper.DoctorListMapper;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.service.doctor.DoctorDirectoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorDirectoryServiceImpl implements DoctorDirectoryService {

    private final DoctorRepository doctorRepository;
    private final DoctorListMapper mapper;

    @PreAuthorize("hasRole('DOCTOR')")
    public List<DoctorListDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}
