package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "string")
public interface DoctorPatientAssignmentMapper {

    DoctorPatientAssignmentMapper INSTANCE = Mappers.getMapper(DoctorPatientAssignmentMapper.class);

    @Mappings({
            @Mapping(source = "patient.nationalId", target = "patientResponse.nationalId"),
            @Mapping(source = "patient.isHealthInsurancePaid", target = "patientResponse.isHealthInsurancePaid"),
            @Mapping(source = "patient.user.firstName", target = "patientResponse.userData.firstName"),
            @Mapping(source = "patient.user.lastName", target = "patientResponse.userData.lastName"),
            @Mapping(source = "doctor.doctorUid", target = "doctorResponse.doctorUid"),
            @Mapping(source = "doctor.user.firstName", target = "doctorResponse.userData.firstName"),
            @Mapping(source = "doctor.user.lastName", target = "doctorResponse.userData.lastName"),
    })
    DoctorPatientAssignmentResponse toDTO(DoctorPatientAssignment doctorPatientAssignment);

    List<DoctorPatientAssignmentResponse> toDTOList(List<DoctorPatientAssignment> doctorPatientAssignments);
}
