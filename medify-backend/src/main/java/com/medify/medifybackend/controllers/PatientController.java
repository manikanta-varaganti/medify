package com.medify.medifybackend.controllers;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.medify.medifybackend.exceptions.UserNotFoundException;
import com.medify.medifybackend.models.Patient;
import com.medify.medifybackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping
    public Iterable<Patient> getAllPatients() {
        return patientService.getAllPatients();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient  = patientService.getPatientById(id).orElseThrow(() -> new RuntimeException("Patient Not Found") );

        return ResponseEntity.ok(patient);

    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient savedPatient = patientService.addPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient newPatient) throws JsonMappingException {
        Optional<Patient> existingPatientOptional = patientService.getPatientById(id);
        if (!existingPatientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Patient existingPatient = existingPatientOptional.get();
        Patient upatedPatient = patientService.updatePatient(existingPatient, newPatient, id);
        return ResponseEntity.ok(upatedPatient);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> deletePatient(@PathVariable Long id) {
        Optional<Patient> existingPatientOptional = patientService.getPatientById(id);
        if (!existingPatientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
