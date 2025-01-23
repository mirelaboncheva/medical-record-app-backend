package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.SickLeaveResponse;
import com.mirela.medicalrecordapp.model.SickLeave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SickLeaveMapper {

    SickLeaveMapper INSTANCE = Mappers.getMapper(SickLeaveMapper.class);

    SickLeaveResponse toDTO(SickLeave sickLeave);
    List<SickLeaveResponse> toDTOList(List<SickLeave> sickLeaves);
}
