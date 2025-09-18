package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorRequestDto;

import java.util.List;

public interface AdminDoctorService {

    List<ManageDoctorDto> getAllDoctors();
    ManageDoctorDto getDoctorById(Long id);
    ManageDoctorDto createDoctor(CreateDoctorRequestDto dto);
    ManageDoctorDto updateDoctor(Long id, UpdateDoctorRequestDto dto);
    void deleteDoctor(Long id);
}
