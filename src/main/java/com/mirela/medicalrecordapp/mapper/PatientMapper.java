package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.dto.admin.ManagePatientDto;
import com.mirela.medicalrecordapp.dto.patient.PatientProfileDto;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserMapper.class)
public interface PatientMapper {

    PatientMapper INSTANCE = Mappers.getMapper(PatientMapper.class);

    @Mappings({
            @Mapping(source = "user.firstName", target = "firstName"),
            @Mapping(source = "user.lastName", target = "lastName"),
            @Mapping(source = "user.phoneNumber", target = "phoneNumber")
    })
    PatientPersonalDataResponse toDTO(Patient patient);

    @Mapping(source = "user.firstName", target = "userData.firstName")
    @Mapping(source = "user.lastName", target = "userData.lastName")
    PatientResponse toPatientResponse(Patient patient);


    List<PatientPersonalDataResponse> toDTOList(List<Patient> patients);

    @Mapping(target = "patientId", source = "id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    @Mapping(target = "nationalId", source = "nationalId")
    @Mapping(target = "healthInsurancePaid", source = "isHealthInsurancePaid")
    @Mapping(
            target = "assignedDoctorName",
            expression = "java(patient.getDoctorPatientAssignment() != null && patient.getDoctorPatientAssignment().getDoctor() != null ? " +
                    "patient.getDoctorPatientAssignment().getDoctor().getUser().getFirstName() + \" \" + " +
                    "patient.getDoctorPatientAssignment().getDoctor().getUser().getLastName() : null)"
    )
    ManagePatientDto toManageDto(Patient patient);

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    @Mapping(target = "nationalId", source = "nationalId")
    @Mapping(target = "healthInsurancePaid", source = "isHealthInsurancePaid")

    // doctor-related fields will be filled in @AfterMapping (to handle null safely)
    @Mapping(target = "personalDoctorName", ignore = true)
    @Mapping(target = "personalDoctorSpecialization", ignore = true)
    @Mapping(target = "personalDoctorPhone", ignore = true)
    PatientProfileDto toDto(Patient patient);

    @AfterMapping
    default void fillDoctorFields(Patient patient, @MappingTarget PatientProfileDto dto) {
        DoctorPatientAssignment assignment = patient.getDoctorPatientAssignment();
        if (assignment == null || assignment.getDoctor() == null) {
            return;
        }
        var doctor = assignment.getDoctor();
        var du = doctor.getUser();

        dto.setPersonalDoctorName(
                (du.getFirstName() != null ? du.getFirstName() : "") +
                        " " +
                        (du.getLastName() != null ? du.getLastName() : "")
        );
        dto.setPersonalDoctorSpecialization(doctor.getSpecialization());
        dto.setPersonalDoctorPhone(du.getPhoneNumber());
    }
}
