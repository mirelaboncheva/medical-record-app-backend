package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateDoctorRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateDoctorRequestDto;

import java.util.List;

public interface AdminDoctorService {

    public List<ManageDoctorDto> getAllDoctors();
    public ManageDoctorDto getDoctorById(Long id);
    public ManageDoctorDto createDoctor(CreateDoctorRequestDto dto);
    public ManageDoctorDto updateDoctor(Long id, UpdateDoctorRequestDto dto);
    public void deleteDoctor(Long id);
}
