package com.mirela.medicalrecordapp.service.patient;

import com.mirela.medicalrecordapp.dto.patient.MySickLeaveDto;

import java.util.List;

public interface MySickLeaveService {

    List<MySickLeaveDto> getMySickLeaves();
}
