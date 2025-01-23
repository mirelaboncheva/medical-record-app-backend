package com.mirela.medicalrecordapp.controller;

import com.mirela.medicalrecordapp.dto.SickLeaveResponse;
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
    public ResponseEntity<List<SickLeaveResponse>> getSickLeaves() {
        return ResponseEntity.ok(sickLeaveService.getSickLeaves());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SickLeaveResponse> getSickLeaveById(@PathVariable Long id) {
        return ResponseEntity.ok(sickLeaveService.getSickLeaveById(id));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSickLeaveById(@PathVariable Long id) {
        sickLeaveService.deleteSickLeave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
