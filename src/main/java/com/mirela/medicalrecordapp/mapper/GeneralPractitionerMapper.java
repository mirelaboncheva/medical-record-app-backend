package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.GPAssignmentDTO;
import com.mirela.medicalrecordapp.model.GeneralPractitioner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {DoctorMapper.class, PatientMapper.class})
public interface GeneralPractitionerMapper {

    GeneralPractitionerMapper INSTANCE = Mappers.getMapper(GeneralPractitionerMapper.class);

    @Mappings({
            @Mapping(source = "patientInfo.id", target = "patient.id"),
            @Mapping(source = "patientInfo.firstName", target = "patient.firstName"),
            @Mapping(source = "patientInfo.lastName", target = "patient.lastName"),
            @Mapping(source = "doctorInfo.id", target = "doctor.id"),
            @Mapping(source = "doctorInfo.firstName", target = "doctor.firstName"),
            @Mapping(source = "doctorInfo.lastName", target = "doctor.lastName"),
            @Mapping(target = "registrationDate", ignore = true),
            @Mapping(target = "deregistrationDate", ignore = true)
    })
    GeneralPractitioner toEntity(GPAssignmentDTO assignmentDTO);

    @Mappings({
            @Mapping(source = "patient.id", target = "patientInfo.id"),
            @Mapping(source = "patient.firstName", target = "patientInfo.firstName"),
            @Mapping(source = "patient.lastName", target = "patientInfo.lastName"),
            @Mapping(source = "doctor.id", target = "doctorInfo.id"),
            @Mapping(source = "doctor.firstName", target = "doctorInfo.firstName"),
            @Mapping(source = "doctor.lastName", target = "doctorInfo.lastName")
    })
    GPAssignmentDTO toDTO(GeneralPractitioner generalPractitioner);
}
