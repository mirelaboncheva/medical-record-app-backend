package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;

import java.util.List;

public interface AdminDoctorService {

    public List<ManageDoctorDto> getAllDoctors();
}
