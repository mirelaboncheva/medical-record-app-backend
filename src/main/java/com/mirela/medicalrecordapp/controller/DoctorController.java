package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.DoctorDTO;
import com.mirela.medicalrecordapp.model.Doctor;
import com.mirela.medicalrecordapp.model.Patient;
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
    public ResponseEntity<List<DoctorDTO>> getDoctors() {
        return ResponseEntity.ok(doctorService.getDoctors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        return ResponseEntity.ok(doctorService.getDoctorById(id));
    }

    @PostMapping
    public ResponseEntity<Doctor> saveDoctor(@RequestBody DoctorDTO doctorDTO) {
        Doctor savedDoctor = doctorService.saveDoctor(doctorDTO);
        return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctorById(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}