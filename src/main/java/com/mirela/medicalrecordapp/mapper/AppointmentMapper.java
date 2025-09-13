package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.AppointmentRequest;
import com.mirela.medicalrecordapp.dto.AppointmentResponse;
import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.model.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper( componentModel = "spring", uses = UserMapper.class)
public interface AppointmentMapper {

    AppointmentMapper INSTANCE = Mappers.getMapper(AppointmentMapper.class);

    @Mappings({
            @Mapping(source = "patient.nationalId", target = "patientResponse.nationalId"),
            @Mapping(source = "patient.isHealthInsurancePaid", target = "patientResponse.isHealthInsurancePaid"),
            @Mapping(source = "patient.user.firstName", target = "patientResponse.userData.firstName"),
            @Mapping(source = "patient.user.lastName", target = "patientResponse.userData.lastName"),
            @Mapping(source = "doctor.doctorUid", target = "doctorResponse.doctorUid"),
            @Mapping(source = "doctor.user.firstName", target = "doctorResponse.userData.firstName"),
            @Mapping(source = "doctor.user.lastName", target = "doctorResponse.userData.lastName")
    })
    AppointmentResponse toDTO(Appointment appointment);

    List<AppointmentResponse> toDTOList(List<Appointment> appointments);

    @Mappings({
            @Mapping(source = "patientId", target = "patient.id"),
            @Mapping(source = "doctorId", target = "doctor.id")
    })
    Appointment toEntity(AppointmentRequest appointmentRequest);

    @Mapping(target = "appointmentId", source = "id")
    @Mapping(target = "patientId", source = "patient.id")
    @Mapping(target = "patientName",
            expression = "java(a.getPatient().getUser().getFirstName() + \" \" + a.getPatient().getUser().getLastName())")
    @Mapping(target = "doctorId", source = "doctor.id")
    @Mapping(target = "doctorName",
            expression = "java(a.getDoctor().getUser().getFirstName() + \" \" + a.getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "doctor.specialization")
    AppointmentDto toDto(Appointment a);
}
