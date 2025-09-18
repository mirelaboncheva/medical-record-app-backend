package com.mirela.medicalrecordapp.dto.doctor;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.util.List;

@Data
public class MyAppointmentDetailsDto {
    private Long appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentHour;
    private Duration duration;
    private String status;
    private String patientName;
    private String nationalId;
    private boolean personalPatient;

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