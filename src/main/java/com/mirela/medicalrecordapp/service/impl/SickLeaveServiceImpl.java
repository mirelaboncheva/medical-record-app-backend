package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.SickLeaveDTO;
import com.mirela.medicalrecordapp.dto.SickLeaveUpdateRequest;
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
    public List<SickLeaveDTO> getSickLeaves() {
        List<SickLeave> sickLeaves = sickLeaveRepository.findAll();
        return sickLeaveMapper.toDTOList(sickLeaves);
    }

    @Override
    public SickLeaveDTO getSickLeaveById(Long id) {
        return sickLeaveRepository.findById(id)
                .map(sickLeaveMapper::toDTO)
                .orElse(null);
    }

    @Override
    public SickLeave saveSickLeave(SickLeaveDTO sickLeaveDTO) {
        var sickLeave = SickLeave.builder()
                .startDate(sickLeaveDTO.getStartDate())
                .endDate(sickLeaveDTO.getEndDate())
                .build();

        return sickLeaveRepository.save(sickLeave);
    }

    @Override
    public SickLeave updateSickLeave(Long id, SickLeaveUpdateRequest sickLeaveUpdateRequest) {
        SickLeave existingSickLeave = sickLeaveRepository.findById(id).orElse(null);

        existingSickLeave.setEndDate(sickLeaveUpdateRequest.getEndDate());
        return sickLeaveRepository.save(existingSickLeave);
    }

    @Override
    public void deleteSickLeave(Long id) {
        sickLeaveRepository.deleteById(id);
    }
}
