package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.SickLeaveResponse;
import com.mirela.medicalrecordapp.mapper.SickLeaveMapper;
import com.mirela.medicalrecordapp.model.SickLeave;
import com.mirela.medicalrecordapp.repository.SickLeaveRepository;
import com.mirela.medicalrecordapp.service.SickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SickLeaveServiceImpl implements SickLeaveService {

    private final SickLeaveRepository sickLeaveRepository;
    private final SickLeaveMapper sickLeaveMapper;


    @Override
    public List<SickLeaveResponse> getSickLeaves() {
        List<SickLeave> sickLeaves = sickLeaveRepository.findAll();
        return sickLeaveMapper.toDTOList(sickLeaves);
    }

    @Override
    public SickLeaveResponse getSickLeaveById(Long id) {
        return sickLeaveRepository.findById(id)
                .map(sickLeaveMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteSickLeave(Long id) {
        sickLeaveRepository.deleteById(id);
    }
}
