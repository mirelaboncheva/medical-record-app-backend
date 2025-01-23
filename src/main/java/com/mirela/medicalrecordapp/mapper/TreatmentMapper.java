package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.TreatmentResponse;
import com.mirela.medicalrecordapp.model.Treatment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentMapper {

    TreatmentMapper INSTANCE = Mappers.getMapper(TreatmentMapper.class);

    TreatmentResponse toDTO(Treatment treatment);

    List<TreatmentResponse> toDTOList(List<Treatment> treatments);
}
