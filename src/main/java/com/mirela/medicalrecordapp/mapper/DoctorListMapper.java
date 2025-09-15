package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.doctor.DoctorListDto;
import com.mirela.medicalrecordapp.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DoctorListMapper {

    @Mapping(target = "doctorId", source = "id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "phoneNumber", source = "user.phoneNumber")
    DoctorListDto toDto(Doctor doctor);
}