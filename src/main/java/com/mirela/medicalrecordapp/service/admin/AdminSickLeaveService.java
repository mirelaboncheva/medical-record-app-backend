package com.mirela.medicalrecordapp.service.admin;

import com.mirela.medicalrecordapp.dto.admin.CreateSickLeaveRequestDto;
import com.mirela.medicalrecordapp.dto.admin.SickLeaveDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateSickLeaveRequestDto;

import java.util.List;

public interface AdminSickLeaveService {

    List<SickLeaveDto> getAllSickLeaves();
    SickLeaveDto getSickLeaveById(Long id);
    SickLeaveDto createSickLeave(CreateSickLeaveRequestDto dto);
    SickLeaveDto updateSickLeave(Long id, UpdateSickLeaveRequestDto dto);
    void deleteSickLeave(Long id);
}
