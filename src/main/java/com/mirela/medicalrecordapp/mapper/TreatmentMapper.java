package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.dto.admin.TreatmentDto;
import com.mirela.medicalrecordapp.model.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);

    TreatmentDTO toDTO(Treatment treatment);

    List<TreatmentDTO> toDTOList(List<Treatment> treatments);

    @Mapping(target = "treatmentId", source = "id")
    @Mapping(target = "appointmentId", source = "appointment.id")
    @Mapping(target = "patientId", source = "appointment.patient.id")
    @Mapping(target = "patientName",
            expression = "java(t.getAppointment().getPatient().getUser().getFirstName() + \" \" + t.getAppointment().getPatient().getUser().getLastName())")
    @Mapping(target = "doctorId", source = "appointment.doctor.id")
    @Mapping(target = "doctorName",
            expression = "java(t.getAppointment().getDoctor().getUser().getFirstName() + \" \" + t.getAppointment().getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "appointment.doctor.specialization")
    TreatmentDto toDto(Treatment t);
}
