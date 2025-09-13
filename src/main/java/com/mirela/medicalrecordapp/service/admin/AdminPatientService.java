package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreatePatientRequestDto;
import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.dto.admin.UpdatePatientRequestDto;

import java.util.List;

public interface AdminPatientService {

    public List<ManagePatientDto> getAllPatients();
    public ManagePatientDto getPatientById(Long id);
    public ManagePatientDto createPatient(CreatePatientRequestDto dto);
    public ManagePatientDto updatePatient(Long id, UpdatePatientRequestDto dto);
    public void deletePatient(Long id);
}
