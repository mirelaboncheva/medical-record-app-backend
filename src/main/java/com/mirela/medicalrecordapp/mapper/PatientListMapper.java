package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.doctor.PatientListDto;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PatientListMapper {

    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "nationalId", source = "nationalId")
    @Mapping(target = "healthInsurancePaid", source = "isHealthInsurancePaid")
    @Mapping(target = "personalDoctorUid", ignore = true)
    @Mapping(target = "personalDoctorName", ignore = true)
    @Mapping(target = "personalDoctorSpecialization", ignore = true)
    PatientListDto toDto(Patient patient);

    @AfterMapping
    default void fillDoctorInfo(Patient patient, @MappingTarget PatientListDto dto) {
        DoctorPatientAssignment assignment = patient.getDoctorPatientAssignment();
        if (assignment != null && assignment.getDoctor() != null) {
            var doctor = assignment.getDoctor();
            var du = doctor.getUser();
            dto.setPersonalDoctorUid(doctor.getDoctorUid());
            dto.setPersonalDoctorName(du.getFirstName() + " " + du.getLastName());
            dto.setPersonalDoctorSpecialization(doctor.getSpecialization());
        }
    }
}