package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.TreatmentDTO;
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
    public List<TreatmentDTO> getTreatments() {
        List<Treatment> treatments = treatmentRepository.findAll();
        return treatmentMapper.toDTOList(treatments);
    }

    @Override
    public TreatmentDTO getTreatmentById(Long id) {
        return treatmentRepository.findById(id)
                .map(treatmentMapper::toDTO)
                .orElse(null);
    }

    @Override
    public Treatment saveTreatment(TreatmentDTO treatmentDTO) {
        var treatment = Treatment.builder()
                .medicine(treatmentDTO.getMedicine())
                .dosage(treatmentDTO.getDosage())
                .duration(treatmentDTO.getDuration())
                .doctorNotes(treatmentDTO.getDoctorNotes())
                .build();

        return treatmentRepository.save(treatment);
    }

    @Override
    public Treatment updateTreatment(Long id, TreatmentDTO treatmentDTO) {
        Treatment existingTreatment = treatmentRepository.findById(id).orElse(null);

        existingTreatment.setMedicine(treatmentDTO.getMedicine());
        existingTreatment.setDosage(treatmentDTO.getDosage());
        existingTreatment.setDuration(treatmentDTO.getDuration());
        existingTreatment.setDoctorNotes(treatmentDTO.getDoctorNotes());

        return treatmentRepository.save(existingTreatment);
    }

    @Override
    public void deleteTreatment(Long id) {
        treatmentRepository.deleteById(id);
    }
}
