package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.DoctorListDto;

import java.util.List;

public interface DoctorDirectoryService {

    List<DoctorListDto> getAllDoctors();
}
