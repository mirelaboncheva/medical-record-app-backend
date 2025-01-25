package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.SickLeaveDTO;
import com.mirela.medicalrecordapp.dto.SickLeaveUpdateRequest;
import com.mirela.medicalrecordapp.model.SickLeave;
import com.mirela.medicalrecordapp.service.SickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sick-leaves")
public class SickLeaveController {

    private final SickLeaveService sickLeaveService;

    @GetMapping
    public ResponseEntity<List<SickLeaveDTO>> getSickLeaves() {
        return ResponseEntity.ok(sickLeaveService.getSickLeaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SickLeaveDTO> getSickLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(sickLeaveService.getSickLeaveById(id));
    }

    @PostMapping
    public ResponseEntity<SickLeave> saveSickLeave(@RequestBody SickLeaveDTO sickLeaveDTO) {
        SickLeave savedSickLeave = sickLeaveService.saveSickLeave(sickLeaveDTO);
        return new ResponseEntity<>(savedSickLeave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SickLeave> updateSickLeave(
            @PathVariable Long id,
            @RequestBody SickLeaveUpdateRequest sickLeaveUpdateRequest) {
        return ResponseEntity.ok(sickLeaveService.updateSickLeave(id, sickLeaveUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSickLeaveById(@PathVariable Long id) {
        sickLeaveService.deleteSickLeave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
