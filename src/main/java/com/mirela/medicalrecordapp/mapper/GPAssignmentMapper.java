package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.GPResponse;
import com.mirela.medicalrecordapp.model.GPAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "string")
public interface GPAssignmentMapper {

    GPAssignmentMapper INSTANCE = Mappers.getMapper(GPAssignmentMapper.class);

    @Mappings({
            @Mapping(source = "patient.nationalId", target = "patientResponse.nationalId"),
            @Mapping(source = "patient.isHealthInsurancePaid", target = "patientResponse.isHealthInsurancePaid"),
            @Mapping(source = "patient.user.firstName", target = "patientResponse.userData.firstName"),
            @Mapping(source = "patient.user.lastName", target = "patientResponse.userData.lastName"),
            @Mapping(source = "doctor.doctorUid", target = "doctorResponse.doctorUid"),
            @Mapping(source = "doctor.user.firstName", target = "doctorResponse.userData.firstName"),
            @Mapping(source = "doctor.user.lastName", target = "doctorResponse.userData.lastName"),
    })
    GPResponse toDTO(GPAssignment gpAssignment);

    List<GPResponse> toDTOList(List<GPAssignment> gpAssignments);
}
