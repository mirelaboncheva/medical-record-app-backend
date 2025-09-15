package com.mirela.medicalrecordapp.mapper;

import com.mirela.medicalrecordapp.dto.PatientBasicDto;
import com.mirela.medicalrecordapp.dto.DoctorCountDto;
import com.mirela.medicalrecordapp.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReportMapper {

    @Mapping(target = "patientId", source = "id")
    @Mapping(target = "firstName", source = "user.firstName")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "nationalId", source = "nationalId")
    PatientBasicDto toPatientBasicDto(Patient patient);

    default DoctorCountDto toDoctorCountDto(Object[] row) {
        if (row == null || row.length < 4) {
            return null;
        }
        return new DoctorCountDto(
                (Long) row[0],
                (String) row[1],
                (String) row[2],
                (Long) row[3]
        );
    }
}
