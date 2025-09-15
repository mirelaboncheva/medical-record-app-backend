package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.dto.DiagnosisCountDto;
import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository extends JpaRepository<Patient, Long> {

    // a. Patients with a given diagnosis
    @Query("""
        SELECT DISTINCT p FROM Patient p
        JOIN p.appointments a
        JOIN a.diagnoses d
        WHERE LOWER(d.name) = LOWER(:diagnosisName)
    """)
    List<Patient> findPatientsWithDiagnosis(String diagnosisName);

    // b. Most common diagnoses
    @Query("""
        SELECT new com.mirela.medicalrecordapp.dto.DiagnosisCountDto(d.name, COUNT(d))
        FROM Diagnosis d
        GROUP BY d.name
        ORDER BY COUNT(d) DESC
    """)
    List<DiagnosisCountDto> findMostCommonDiagnoses();

    // c. Patients of a given personal physician
    @Query("""
        SELECT p FROM Patient p
        JOIN p.DoctorPatientAssignment dpa
        WHERE dpa.doctor.id = :doctorId
        AND dpa.deregistrationDate IS NULL
    """)
    List<Patient> findPatientsByPersonalDoctor(Long doctorId);

    // d. Number of patients per personal physician
    @Query("""
        SELECT d.id, d.user.firstName, d.user.lastName, COUNT(p)
        FROM DoctorPatientAssignment dpa
        JOIN dpa.doctor d
        JOIN dpa.patient p
        WHERE dpa.deregistrationDate IS NULL
        GROUP BY d.id, d.user.firstName, d.user.lastName
    """)
    List<Object[]> countPatientsPerDoctor();

    // e. Number of visits to each physician
    @Query("""
        SELECT d.id, d.user.firstName, d.user.lastName, COUNT(a)
        FROM Appointment a
        JOIN a.doctor d
        GROUP BY d.id, d.user.firstName, d.user.lastName
    """)
    List<Object[]> countVisitsPerDoctor();

    // f. Visits to each patient
    @Query("""
        SELECT a FROM Appointment a
        WHERE a.patient.id = :patientId
        ORDER BY a.appointmentDate DESC
    """)
    List<Appointment> findVisitsByPatient(Long patientId);

    // g. Exams with all physicians in a given period
    @Query("""
        SELECT a FROM Appointment a
        WHERE a.appointmentDate BETWEEN :start AND :end
        ORDER BY a.appointmentDate DESC
    """)
    List<Appointment> findExamsAllDoctorsInPeriod(LocalDate start, LocalDate end);

    // h. Exams with a specific physician in a given period
    @Query("""
        SELECT a FROM Appointment a
        WHERE a.doctor.id = :doctorId
        AND a.appointmentDate BETWEEN :start AND :end
        ORDER BY a.appointmentDate DESC
    """)
    List<Appointment> findExamsByDoctorInPeriod(Long doctorId, LocalDate start, LocalDate end);

    // i. Month with most sick leave issued
    @Query("""
        SELECT MONTH(sl.startDate), COUNT(sl)
        FROM SickLeave sl
        GROUP BY MONTH(sl.startDate)
        ORDER BY COUNT(sl) DESC
    """)
    List<Object[]> monthWithMostSickLeaves();

    // j. Physician(s) who issued the most sick leave
    @Query("""
        SELECT d.user.firstName, d.user.lastName, COUNT(sl)
        FROM SickLeave sl
        JOIN sl.appointment a
        JOIN a.doctor d
        GROUP BY d.user.firstName, d.user.lastName
        ORDER BY COUNT(sl) DESC
    """)
    List<Object[]> doctorWithMostSickLeaves();
}
