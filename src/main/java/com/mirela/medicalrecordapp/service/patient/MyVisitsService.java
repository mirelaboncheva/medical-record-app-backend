package com.mirela.medicalrecordapp.service.patient;

import com.mirela.medicalrecordapp.dto.patient.MyVisitDetailsDto;
import com.mirela.medicalrecordapp.dto.patient.MyVisitSummaryDto;

import java.util.List;

public interface MyVisitsService {

    List<MyVisitSummaryDto> getMyVisits();
    MyVisitDetailsDto getMyVisitDetails(Long appointmentId);

}
