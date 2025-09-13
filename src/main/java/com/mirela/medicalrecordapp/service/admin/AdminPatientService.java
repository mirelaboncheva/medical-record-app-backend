package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreatePatientRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.dto.admin.UpdatePatientRequestDto;

import java.util.List;

public interface AdminPatientService {

    List<ManagePatientDto> getAllPatients();
    ManagePatientDto getPatientById(Long id);
    ManagePatientDto createPatient(CreatePatientRequestDto dto);
    ManagePatientDto updatePatient(Long id, UpdatePatientRequestDto dto);
    void deletePatient(Long id);
}
