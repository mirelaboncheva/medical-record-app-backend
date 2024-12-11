package com.mirela.medicalrecordapp.service.impl;

import com.mirela.medicalrecordapp.dto.DoctorDTO;
import com.mirela.medicalrecordapp.mapper.DoctorMapper;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.repository.DoctorRepository;
import com.mirela.medicalrecordapp.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

    @Override
    public List<DoctorDTO> getDoctors(){
        List<Doctor> doctors = doctorRepository.findAll();
        return doctorMapper.toDTOlist(doctors);
    }

    @Override
    public DoctorDTO getDoctorById(Long id){
        return doctorRepository.findById(id)
                .map(doctorMapper::toDTO)
                .orElse(null);
    }

    @Override
    public Doctor saveDoctor(DoctorDTO doctorDTO){
        return doctorRepository.save(doctorMapper.toEntity(doctorDTO));
    }

    @Override
    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
}
