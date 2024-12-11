package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.PatientDTO;
import com.mirela.medicalrecordapp.dto.PatientInfo;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "isHealthInsurancePaid", ignore = true),
            @Mapping(target = "generalPractitioner", ignore = true)
    })
    Patient toEntity(PatientDTO patientDTO);
    PatientDTO toDTO(Patient patient);
    List<PatientDTO> toDTOList(List<Patient> patients);

    @Mappings({
            @Mapping(target = "nationalId", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "isHealthInsurancePaid", ignore = true),
            @Mapping(target = "generalPractitioner", ignore = true)
    })
    Patient fromPatientInfo(PatientInfo patientInfo);
    PatientInfo toPatientInfo(Patient patient);
}
