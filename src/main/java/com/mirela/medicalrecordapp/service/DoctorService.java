package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.DoctorDTO;
import com.mirela.medicalrecordapp.model.Doctor;

import java.util.List;

public interface DoctorService {
    List<DoctorDTO> getDoctors();
    DoctorDTO getDoctorById(Long id);
    Doctor saveDoctor(DoctorDTO doctorDTO);
    //DoctorDTO updateDoctor(SaveDoctorDTO saveDoctorsDTO, Long id);
    void deleteDoctor(Long id);
}
