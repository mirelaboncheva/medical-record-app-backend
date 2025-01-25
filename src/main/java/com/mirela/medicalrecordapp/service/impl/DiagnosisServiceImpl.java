package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.DiagnosisRequest;
import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.mapper.DiagnosisMapper;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.repository.DiagnosisRepository;
import com.mirela.medicalrecordapp.service.DiagnosisService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {

    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;

    @Override
    public List<DiagnosisResponse> getDiagnoses() {
        List<Diagnosis> diagnoses = diagnosisRepository.findAll();
        return diagnosisMapper.toDTOList(diagnoses);
    }

    @Override
    public DiagnosisResponse getDiagnosisById(Long id) {
        return diagnosisRepository.findById(id)
                .map(diagnosisMapper::toDTO)
                .orElse(null);
    }

    @Override
    public DiagnosisResponse getDiagnosisByName(String name) {
        return diagnosisRepository.findByName(name)
                .map(diagnosisMapper::toDTO)
                .orElse(null); //TODO
    }

    @Override
    public Diagnosis saveDiagnosis(DiagnosisRequest diagnosisRequest) {
        var diagnosis = Diagnosis.builder()
                .name(diagnosisRequest.getName())
                .build();

        return diagnosisRepository.save(diagnosis);
    }

    @Override
    public Diagnosis updateDiagnosis(Long id, DiagnosisRequest diagnosisRequest) {
        Diagnosis existingDiagnosis = diagnosisRepository.findById(id).orElse(null);

        existingDiagnosis.setName(diagnosisRequest.getName());
        return diagnosisRepository.save(existingDiagnosis);
    }

    @Override
    public void deleteDiagnosis(Long id) {
        diagnosisRepository.deleteById(id);
    }
}
