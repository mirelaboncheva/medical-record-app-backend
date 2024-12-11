package com.mirela.medicalrecordapp.service;

import com.mirela.medicalrecordapp.dto.GPAssignmentDTO;
import com.mirela.medicalrecordapp.model.GeneralPractitioner;

public interface GeneralPractitionerService {
    GeneralPractitioner saveGeneralPractitioner(GPAssignmentDTO assignmentDTO);
}
