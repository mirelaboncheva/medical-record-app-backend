package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.SickLeaveDTO;
import com.mirela.medicalrecordapp.model.SickLeave;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SickLeaveMapper {

    SickLeaveMapper INSTANCE = Mappers.getMapper(SickLeaveMapper.class);

    SickLeaveDTO toDTO(SickLeave sickLeave);
    List<SickLeaveDTO> toDTOList(List<SickLeave> sickLeaves);
}
