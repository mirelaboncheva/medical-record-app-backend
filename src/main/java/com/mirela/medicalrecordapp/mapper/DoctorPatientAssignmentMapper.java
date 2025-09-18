package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.admin.DoctorPatientAssignmentResponseDto;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface DoctorPatientAssignmentMapper {

    DoctorPatientAssignmentMapper INSTANCE = Mappers.getMapper(DoctorPatientAssignmentMapper.class);

    @Mappings({
            @Mapping(source = "patient.nationalId", target = "patientResponse.nationalId"),
            @Mapping(source = "patient.isHealthInsurancePaid", target = "patientResponse.isHealthInsurancePaid"),
            @Mapping(source = "patient.user.firstName", target = "patientResponse.userData.firstName"),
            @Mapping(source = "patient.user.lastName", target = "patientResponse.userData.lastName"),
            @Mapping(source = "doctor.doctorUid", target = "doctorResponse.doctorUid"),
            @Mapping(source = "doctor.user.firstName", target = "doctorResponse.userData.firstName"),
            @Mapping(source = "doctor.user.lastName", target = "doctorResponse.userData.lastName")
    })
    DoctorPatientAssignmentResponse toDTO(DoctorPatientAssignment doctorPatientAssignment);

    List<DoctorPatientAssignmentResponse> toDTOList(List<DoctorPatientAssignment> doctorPatientAssignments);

    @Mapping(target = "assignmentId", source = "id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName", expression = "java(assignment.getPatient().getUser().getFirstName() + \" \" + assignment.getPatient().getUser().getLastName())")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "doctorName", expression = "java(assignment.getDoctor().getUser().getFirstName() + \" \" + assignment.getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "doctor.specialization")
    DoctorPatientAssignmentResponseDto toDto(DoctorPatientAssignment assignment);
}
