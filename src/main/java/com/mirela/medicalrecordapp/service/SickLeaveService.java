package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.*;
import com.mirela.medicalrecordapp.model.SickLeave;

import java.util.List;

public interface SickLeaveService {
    List<SickLeaveDTO> getSickLeaves();
    SickLeaveDTO getSickLeaveById(Long id);
    SickLeave saveSickLeave(SickLeaveDTO sickLeaveDTO);
    SickLeave updateSickLeave(Long id, SickLeaveUpdateRequest sickLeaveUpdateRequest);
    void deleteSickLeave(Long id);
}
