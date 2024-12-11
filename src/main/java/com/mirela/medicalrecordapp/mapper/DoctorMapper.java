package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DoctorDTO;
import com.mirela.medicalrecordapp.dto.DoctorInfo;
import com.mirela.medicalrecordapp.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);

    @Mapping(target = "personalPatients", ignore = true)
    Doctor toEntity(DoctorDTO doctorDTO);
    DoctorDTO toDTO(Doctor doctor);
    List<DoctorDTO> toDTOlist(List<Doctor> doctors);

    @Mappings({
            @Mapping(target = "doctorUid", ignore = true),
            @Mapping(target = "phoneNumber", ignore = true),
            @Mapping(target = "email", ignore = true),
            @Mapping(target = "specialization", ignore = true),
            @Mapping(target = "personalPatients", ignore = true)
    })
    Doctor fromDoctorInfo(DoctorInfo doctorInfo);
    DoctorInfo toDoctorInfo(Doctor doctor);
}
