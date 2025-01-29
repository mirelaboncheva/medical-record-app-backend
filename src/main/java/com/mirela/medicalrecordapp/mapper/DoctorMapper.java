package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DoctorPersonalDataResponse;
import com.mirela.medicalrecordapp.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
}
