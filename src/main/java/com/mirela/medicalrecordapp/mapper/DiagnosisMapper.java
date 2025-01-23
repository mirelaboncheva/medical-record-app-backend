package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.DiagnosisResponse;
import com.mirela.medicalrecordapp.dto.PatientPersonalDataResponse;
import com.mirela.medicalrecordapp.model.Diagnosis;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DiagnosisMapper {

    DiagnosisMapper INSTANCE = Mappers.getMapper(DiagnosisMapper.class);

    DiagnosisResponse toDTO(Diagnosis diagnosis);

    List<DiagnosisResponse> toDTOList(List<Diagnosis> diagnosisList);
}
