package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.TreatmentResponse;
import com.mirela.medicalrecordapp.mapper.TreatmentMapper;
import com.mirela.medicalrecordapp.model.Treatment;
import com.mirela.medicalrecordapp.repository.TreatmentRepository;
import com.mirela.medicalrecordapp.service.TreatmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentServiceImpl implements TreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final TreatmentMapper treatmentMapper;


    @Override
    public List<TreatmentResponse> getTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatmentMapper.toDTOList(treatments);
    }

    @Override
    public TreatmentResponse getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .map(treatmentMapper::toDTO)
                .orElse(null);
    }

    @Override
    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }
}
