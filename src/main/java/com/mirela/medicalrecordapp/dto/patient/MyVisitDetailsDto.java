package com.mirela.medicalrecordapp.dto.patient;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.List;

@Data
public class MyVisitDetailsDto {
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private String status;
    private String doctorName;
    private String specialization;
    private boolean personalDoctor;

    private List<DiagnosisDto> diagnoses;
    private List<TreatmentDto> treatments;
    private SickLeaveDto sickLeave;

    @Data
    public static class DiagnosisDto {
        private String name;
    }

    @Data
    public static class TreatmentDto {
        private String medicine;
        private String dosage;
        private String duration;
        private String doctorNotes;
    }

    @Data
    public static class SickLeaveDto {
        private String startDate;
        private String endDate;
    }
}