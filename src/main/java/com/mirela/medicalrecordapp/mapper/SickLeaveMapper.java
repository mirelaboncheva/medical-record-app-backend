package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.SickLeaveDTO;
import com.mirela.medicalrecordapp.dto.admin.SickLeaveDto;
import com.mirela.medicalrecordapp.model.SickLeave;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SickLeaveMapper {

    SickLeaveMapper INSTANCE = Mappers.getMapper(SickLeaveMapper.class);

    SickLeaveDTO toDTO(SickLeave sickLeave);
    List<SickLeaveDTO> toDTOList(List<SickLeave> sickLeaves);

    @Mapping(target = "sickLeaveId", source = "id")
    @Mapping(target = "appointmentId", source = "appointment.id")
    @Mapping(target = "patientId", source = "appointment.patient.id")
    @Mapping(target = "patientName",
            expression = "java(s.getAppointment().getPatient().getUser().getFirstName() + \" \" + s.getAppointment().getPatient().getUser().getLastName())")
    @Mapping(target = "doctorId", source = "appointment.doctor.id")
    @Mapping(target = "doctorName",
            expression = "java(s.getAppointment().getDoctor().getUser().getFirstName() + \" \" + s.getAppointment().getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "appointment.doctor.specialization")
    SickLeaveDto toDto(SickLeave s);
}
