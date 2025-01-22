package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DoctorPersonalDataResponse;

import java.util.List;

public interface DoctorService {
    List<DoctorPersonalDataResponse> getDoctors();
    DoctorPersonalDataResponse getDoctorById(Long id);
    void deleteDoctor(Long id);
}
