package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DoctorPersonalDataResponse;
import com.mirela.medicalrecordapp.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @GetMapping
    public ResponseEntity<List<DoctorPersonalDataResponse>> getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorPersonalDataResponse> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
