package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;

import java.util.List;

public interface AdminPatientService {

    public List<ManagePatientDto> getAllPatients();
}
