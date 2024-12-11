package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.GPAssignmentDTO;
import com.mirela.medicalrecordapp.mapper.GeneralPractitionerMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.GeneralPractitioner;
import com.mirela.medicalrecordapp.model.Patient;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.repository.GeneralPractitionerRepository;
import com.mirela.medicalrecordapp.repository.PatientRepository;
import com.mirela.medicalrecordapp.service.GeneralPractitionerService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class GPServiceImpl implements GeneralPractitionerService {

    private final GeneralPractitionerRepository generalPractitionerRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final GeneralPractitionerMapper generalPractitionerMapper;

    public GeneralPractitioner saveGeneralPractitioner(GPAssignmentDTO assignmentDTO){
        // Fetch Patient and Doctor from repositories
        Patient patient = patientRepository.findById(assignmentDTO.patientInfo().id())
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + assignmentDTO.patientInfo().id()));

        Doctor doctor = doctorRepository.findById(assignmentDTO.doctorInfo().id())
                .orElseThrow(() -> new EntityNotFoundException("Doctor not found with id: " + assignmentDTO.doctorInfo().id()));

        // Check if the patient is already assigned
        if (generalPractitionerRepository.findById(patient.getId()).isPresent()) {
            throw new IllegalStateException("Patient is already assigned to a GP.");
        }

        // Create and save the GP assignment
        GeneralPractitioner gp = new GeneralPractitioner();
        gp.setPatient(patient);
        gp.setDoctor(doctor);
        gp.setRegistrationDate(LocalDate.now());
        gp.setDeregistrationDate(LocalDate.now().plusYears(1));

        // Save the General Practitioner entity
        return generalPractitionerRepository.save(gp); // Return the saved entity
    }
}