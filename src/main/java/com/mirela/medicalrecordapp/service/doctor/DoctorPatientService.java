package com.mirela.medicalrecordapp.service.doctor;

import com.mirela.medicalrecordapp.dto.doctor.PatientListDto;

import java.util.List;

public interface DoctorPatientService {

    List<PatientListDto> getAllPatients();
}
