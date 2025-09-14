package com.mirela.medicalrecordapp.service.admin.impl;

import com.mirela.medicalrecordapp.dto.admin.CreateTreatmentRequestDto;
import com.mirela.medicalrecordapp.dto.admin.TreatmentDto;
import com.mirela.medicalrecordapp.dto.admin.UpdateTreatmentRequestDto;
import com.mirela.medicalrecordapp.mapper.TreatmentMapper;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Treatment;
import com.mirela.medicalrecordapp.repository.AppointmentRepository;
import com.mirela.medicalrecordapp.repository.TreatmentRepository;
import com.mirela.medicalrecordapp.service.admin.AdminTreatmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminTreatmentServiceImpl implements AdminTreatmentService {

    private final TreatmentRepository treatmentRepository;
    private final AppointmentRepository appointmentRepository;
    private final TreatmentMapper mapper;

    @PreAuthorize("hasRole('ADMIN')")
    public List<TreatmentDto> getAllTreatments() {
        return treatmentRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @PreAuthorize("hasRole('ADMIN')")
    public TreatmentDto getTreatmentById(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found"));
        return mapper.toDto(treatment);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public TreatmentDto createTreatment(CreateTreatmentRequestDto dto) {
        Appointment appointment = appointmentRepository.findById(dto.getAppointmentId())
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));

        Treatment treatment = new Treatment();
        treatment.setMedicine(dto.getMedicine());
        treatment.setDosage(dto.getDosage());
        treatment.setDuration(dto.getDuration());
        treatment.setDoctorNotes(dto.getDoctorNotes());
        treatment.setAppointment(appointment);

        treatmentRepository.save(treatment);
        return mapper.toDto(treatment);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public TreatmentDto updateTreatment(Long id, UpdateTreatmentRequestDto dto) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found"));

        treatment.setMedicine(dto.getMedicine());
        treatment.setDosage(dto.getDosage());
        treatment.setDuration(dto.getDuration());
        treatment.setDoctorNotes(dto.getDoctorNotes());

        return mapper.toDto(treatment);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTreatment(Long id) {
        Treatment treatment = treatmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Treatment not found"));
        treatmentRepository.delete(treatment);
    }
}