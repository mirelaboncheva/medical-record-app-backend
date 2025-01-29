package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.AssignPatientDoctorRequest;
import com.mirela.medicalrecordapp.dto.DoctorPatientAssignmentResponse;
import com.mirela.medicalrecordapp.dto.DoctorPatientCountResponse;
import com.mirela.medicalrecordapp.dto.PatientResponse;
import com.mirela.medicalrecordapp.model.DoctorPatientAssignment;
import com.mirela.medicalrecordapp.service.DoctorPatientAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/gp-assignments")
public class DoctorPatientAssignmentController {

    private final DoctorPatientAssignmentService doctorPatientAssignmentService;

    @GetMapping
    public ResponseEntity<List<DoctorPatientAssignmentResponse>> getDoctorPatientAssignments() {
        return ResponseEntity.ok(doctorPatientAssignmentService.getDoctorPatientAssignments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorPatientAssignmentResponse> getDoctorPatientAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorPatientAssignmentService.getDoctorPatientAssignmentById(id));
    }

    @GetMapping("/{doctorId}/patients")
    public ResponseEntity<List<PatientResponse>> getPatientsByDoctor(@PathVariable Long doctorId) {
        List<PatientResponse> patients = doctorPatientAssignmentService.getPatientsByDoctorId(doctorId);
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/patient-counts")
    public ResponseEntity<List<DoctorPatientCountResponse>> getDoctorPatientCounts() {
        List<DoctorPatientCountResponse> response = doctorPatientAssignmentService.getDoctorPatientCounts();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<DoctorPatientAssignment> saveDoctorPatientAssignment(AssignPatientDoctorRequest request){
        DoctorPatientAssignment assignment = doctorPatientAssignmentService.saveAssignment(request);
        return new ResponseEntity<>(assignment,  HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorPatientAssignmentById(@PathVariable Long id) {
        doctorPatientAssignmentService.deleteDoctorPatientAssignment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
