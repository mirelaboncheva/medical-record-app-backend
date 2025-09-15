package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.doctor.DoctorProfileDto;
import com.mirela.medicalrecordapp.dto.DoctorPersonalDataResponse;
import com.mirela.medicalrecordapp.dto.admin.ManageDoctorDto;
import com.mirela.medicalrecordapp.dto.doctor.MyAppointmentDetailsDto;
import com.mirela.medicalrecordapp.dto.doctor.MyAppointmentSummaryDto;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Doctor;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mappings({
            @Mapping(source = "user.firstName", target = "firstName"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    })
    DoctorPersonalDataResponse toDTO(Doctor doctor);

    List<DoctorPersonalDataResponse> toDTOList(List<Doctor> doctors);

    @Mapping(target = "doctorId", source = "id")
    @Mapping(target = "doctorUid", source = "doctorUid")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    @Mapping(target = "specialization", source = "specialization")
    @Mapping(target = "assignedPatientsCount", expression = "java(doctor.getPersonalPatients() != null ? doctor.getPersonalPatients().size() : 0)")
    ManageDoctorDto toManageDto(Doctor doctor);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    DoctorProfileDto toDto(Doctor doctor);

    @Mapping(target = "patientName", expression = "java(a.getPatient().getUser().getFirstName() + \" \" + a.getPatient().getUser().getLastName())")
    @Mapping(target = "nationalId", source = "patient.nationalId")
    @Mapping(target = "personalPatient", ignore = true)
    MyAppointmentSummaryDto toSummaryDto(Appointment a);

    @Mapping(target = "patientName", expression = "java(a.getPatient().getUser().getFirstName() + \" \" + a.getPatient().getUser().getLastName())")
    @Mapping(target = "nationalId", source = "patient.nationalId")
    @Mapping(target = "personalPatient", ignore = true)
    @Mapping(target = "diagnoses", expression = "java(a.getDiagnoses().stream().map(d -> { var dto = new MyAppointmentDetailsDto.DiagnosisDto(); dto.setName(d.getName()); return dto; }).toList())")
    @Mapping(target = "treatments", expression = "java(a.getTreatments().stream().map(t -> { var dto = new MyAppointmentDetailsDto.TreatmentDto(); dto.setMedicine(t.getMedicine()); dto.setDosage(t.getDosage()); dto.setDuration(t.getDuration()); dto.setDoctorNotes(t.getDoctorNotes()); return dto; }).toList())")
    @Mapping(target = "sickLeave", expression = "java(a.getSickLeave() != null ? mapSickLeave(a) : null)")
    MyAppointmentDetailsDto toDetailsDto(Appointment a);

    default MyAppointmentDetailsDto.SickLeaveDto mapSickLeave(Appointment a) {
        var sl = a.getSickLeave();
        var dto = new MyAppointmentDetailsDto.SickLeaveDto();
        dto.setStartDate(sl.getStartDate().toString());
        dto.setEndDate(sl.getEndDate().toString());
        return dto;
    }

    @AfterMapping
    default void setPersonalPatient(Appointment a, @MappingTarget MyAppointmentSummaryDto dto) {
        var assignment = a.getPatient().getDoctorPatientAssignment();
        dto.setPersonalPatient(assignment != null && assignment.getDoctor().getId().equals(a.getDoctor().getId()));
    }

    @AfterMapping
    default void setPersonalPatientDetails(Appointment a, @MappingTarget MyAppointmentDetailsDto dto) {
        var assignment = a.getPatient().getDoctorPatientAssignment();
        dto.setPersonalPatient(assignment != null && assignment.getDoctor().getId().equals(a.getDoctor().getId()));
    }

}
