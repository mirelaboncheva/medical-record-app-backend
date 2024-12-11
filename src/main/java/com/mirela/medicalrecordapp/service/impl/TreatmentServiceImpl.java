package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.PatientDTO;
import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.mapper.TreatmentMapper;
import com.mirela.medicalrecordapp.model.Patient;
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
    public List<TreatmentDTO> getTreatments(){
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatmentMapper.toDtoList(treatments);
    }

    @Override
    public TreatmentDTO getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .map(treatmentMapper::toDto)
                .orElse(null); //change null
    }

    @Override
    public Treatment saveTreatment(TreatmentDTO treatmentDTO) {
        return treatmentRepository.save(treatmentMapper.toEntity(treatmentDTO));
    }

    @Override
    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }
}
