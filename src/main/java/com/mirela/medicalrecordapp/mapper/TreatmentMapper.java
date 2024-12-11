package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.TreatmentDTO;
import com.mirela.medicalrecordapp.model.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);

    @Mapping(target = "appointment", ignore = true)
    Treatment toEntity(TreatmentDTO treatmentDTO);
    TreatmentDTO toDto(Treatment treatment);
    List<TreatmentDTO> toDtoList(List<Treatment> treatments);
}
