package com.mirela.medicalrecordapp.repository;

import com.mirela.medicalrecordapp.model.Appointment;
import com.mirela.medicalrecordapp.model.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @EntityGraph(attributePaths = {"patient.user", "doctor.user"})
    List<Appointment> findAll();

    List<Appointment> findByDoctorId(Long doctorId);
    List<Appointment> findByPatientId(Long patientId);
    @Query("SELECT a.patient FROM Appointment a JOIN a.diagnoses d WHERE d.name = :diagnose")
    List<Patient> getPatientsWithDiagnose(@Param("diagnose") String diagnose);

    @Query("SELECT d.name, COUNT(d.name) FROM Diagnosis d GROUP BY d.name ORDER BY COUNT(d.name) DESC")
    List<Object[]> countMostFrequentDiagnoses();

    @Query("SELECT a.doctor.id, COUNT(a.id) FROM Appointment a GROUP BY a.doctor.id")
    List<Object[]> countAppointmentsForEveryDoctor();

    @Query("SELECT a.doctor.id, COUNT(sl.id) FROM Appointment a LEFT JOIN a.sickLeave sl WHERE sl IS NOT NULL GROUP BY a.doctor.id ORDER BY COUNT(sl.id) DESC")
    List<Object[]> getDoctorWithMostSickLeaves();

    List<Appointment> findByDoctorIdAndAppointmentDate(Long doctorId, LocalDate appointmentDate);
    List<Appointment> findByPatientIdAndAppointmentDate(Long patientId, LocalDate appointmentDate);

    @EntityGraph(attributePaths = {
            "doctor.user",
            "patient.DoctorPatientAssignment.doctor",
            "diagnoses",
            "treatments",
            "sickLeave"
    })
    List<Appointment> findByPatientIdOrderByAppointmentDateDesc(Long patientId);

    @EntityGraph(attributePaths = {
            "patient.user",
            "patient.DoctorPatientAssignment.doctor",
            "diagnoses",
            "treatments",
            "sickLeave"
    })
    List<Appointment> findByDoctorIdOrderByAppointmentDateDesc(Long doctorId);

    boolean existsByDoctorIdAndAppointmentDateAndAppointmentHour(Long doctorId, LocalDate date, LocalTime time);

    boolean existsByPatientIdAndAppointmentDateAndAppointmentHour(Long patientId, LocalDate date, LocalTime time);


    @Query(value = """
    SELECT COUNT(*)
    FROM appointment a
    WHERE a.doctor_id = :doctorId
      AND a.appointment_date = :date
      AND a.appointment_hour < :endTime
      AND (a.appointment_hour + a.duration) > :startTime
    """, nativeQuery = true)
    Long countOverlappingAppointments(
            @Param("doctorId") Long doctorId,
            @Param("date") LocalDate date,
            @Param("startTime") LocalTime startTime,
            @Param("endTime") LocalTime endTime);

}
