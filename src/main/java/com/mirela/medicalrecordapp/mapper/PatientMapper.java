package com.mirela.medicalrecordapp.mapper;


import com.mirela.medicalrecordapp.dto.PatientRequest;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);
    Patient toEntity(PatientRequest patientRequest);
    PatientRequest toDTO(Patient patient);
    List<Patient> toDTOList(List<Patient> patients);
}
