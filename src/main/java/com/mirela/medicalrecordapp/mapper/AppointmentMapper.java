package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.AppointmentRequest;
import com.mirela.medicalrecordapp.dto.AppointmentResponse;
import com.mirela.medicalrecordapp.dto.admin.AppointmentDto;
import com.mirela.medicalrecordapp.dto.patient.MyVisitDetailsDto;
import com.mirela.medicalrecordapp.dto.patient.MyVisitSummaryDto;
import com.mirela.medicalrecordapp.model.Appointment;
import org.mapstruct.*;
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

    List<AppointmentDto> toDtoList(List<Appointment> appointments);

    @Mapping(target = "appointmentId", source = "id")
    @Mapping(target = "doctorName", expression = "java(a.getDoctor().getUser().getFirstName() + \" \" + a.getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "doctor.specialization")
    @Mapping(target = "personalDoctor", ignore = true)
    MyVisitSummaryDto toSummaryDto(Appointment a);

    @Mapping(target = "appointmentId", source = "id")
    @Mapping(target = "doctorName", expression = "java(a.getDoctor().getUser().getFirstName() + \" \" + a.getDoctor().getUser().getLastName())")
    @Mapping(target = "specialization", source = "doctor.specialization")
    @Mapping(target = "personalDoctor", ignore = true)
    @Mapping(target = "diagnoses", expression = "java(a.getDiagnoses().stream().map(d -> { var dto = new MyVisitDetailsDto.DiagnosisDto(); dto.setName(d.getName()); return dto; }).toList())")
    @Mapping(target = "treatments", expression = "java(a.getTreatments().stream().map(t -> { var dto = new MyVisitDetailsDto.TreatmentDto(); dto.setMedicine(t.getMedicine()); dto.setDosage(t.getDosage()); dto.setDuration(t.getDuration()); dto.setDoctorNotes(t.getDoctorNotes()); return dto; }).toList())")
    @Mapping(target = "sickLeave", expression = "java(a.getSickLeave() != null ? mapSickLeave(a) : null)")
    MyVisitDetailsDto toDetailsDto(Appointment a);

    default MyVisitDetailsDto.SickLeaveDto mapSickLeave(Appointment a) {
        var sl = a.getSickLeave();
        var dto = new MyVisitDetailsDto.SickLeaveDto();
        dto.setStartDate(sl.getStartDate().toString());
        dto.setEndDate(sl.getEndDate().toString());
        return dto;
    }

    @AfterMapping
    default void setPersonalDoctor(Appointment a, @MappingTarget MyVisitSummaryDto dto) {
        var assignment = a.getPatient().getDoctorPatientAssignment();
        dto.setPersonalDoctor(assignment != null && assignment.getDoctor().getId().equals(a.getDoctor().getId()));
    }

    @AfterMapping
    default void setPersonalDoctorDetails(Appointment a, @MappingTarget MyVisitDetailsDto dto) {
        var assignment = a.getPatient().getDoctorPatientAssignment();
        dto.setPersonalDoctor(assignment != null && assignment.getDoctor().getId().equals(a.getDoctor().getId()));
    }
}
