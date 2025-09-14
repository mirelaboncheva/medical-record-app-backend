package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.dto.admin.DiagnosisDto;
import com.mirela.medicalrecordapp.dto.patient.MyDiagnosisDto;
import com.mirela.medicalrecordapp.model.Diagnosis;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    DiagnosisMapper INSTANCE = Mappers.getMapper(DiagnosisMapper.class);

    DiagnosisResponse toDTO(Diagnosis diagnosis);

    List<DiagnosisResponse> toDTOList(List<Diagnosis> diagnosisList);

    @Mapping(target = "diagnosisId", source = "id")
    @Mapping(target = "appointmentId", source = "appointment.id")
    @Mapping(target = "patientId", source = "appointment.patient.id")
    @Mapping(target = "patientName",
            expression = "java(d.getAppointment().getPatient().getUser().getFirstName() + \" \" + d.getAppointment().getPatient().getUser().getLastName())")
    @Mapping(target = "doctorId", source = "appointment.doctor.id")
    @Mapping(target = "doctorName",
            expression = "java(d.getAppointment().getDoctor().getUser().getFirstName() + \" \" + d.getAppointment().getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "appointment.doctor.specialization")
    DiagnosisDto toDto(Diagnosis d);

    @Mapping(target = "appointmentDate", source = "appointment.appointmentDate")
    @Mapping(target = "doctorName", expression = "java(d.getAppointment().getDoctor().getUser().getFirstName() + \" \" + d.getAppointment().getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "appointment.doctor.specialization")
    MyDiagnosisDto toDTo(Diagnosis d);
}
