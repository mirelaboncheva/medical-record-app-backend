package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(source = "user.firstName", target = "firstName"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    })
    PatientPersonalDataResponse toDTO(Patient patient);

    @Mapping(source = "user.firstName", target = "userData.firstName")
    @Mapping(source = "user.lastName", target = "userData.lastName")
    PatientResponse toPatientResponse(Patient patient);


    List<PatientPersonalDataResponse> toDTOList(List<Patient> patients);
}
