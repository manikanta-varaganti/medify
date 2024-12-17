package com.medify.medifybackend.controllers;

import com.medify.medifybackend.models.Doctor;
import com.medify.medifybackend.services.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/doctors")
@Validated
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public Iterable<Doctor> getAllDoctors(){
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id){
        Doctor doctor  = doctorService.getDoctorById(id).orElseThrow(() -> new RuntimeException("Doctor Not Found") );

        return ResponseEntity.ok(doctor);
    }

    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@Valid @RequestBody Doctor doctor){
        Doctor savedDoctor = doctorService.addDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @Valid @RequestBody Doctor newDoctor){
        Optional<Doctor> existingDoctorOptional = doctorService.getDoctorById(id);
        if(!existingDoctorOptional.isPresent()){
            return ResponseEntity.notFound().build();
        }
        Doctor updatedDoctor = doctorService.updateDoctor(id, newDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> deleteDoctor(@PathVariable Long id) {
        Optional<Doctor> existingDoctorOptional = doctorService.getDoctorById(id);
        if (!existingDoctorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doctorService.deleteDoctorById(id);
        return ResponseEntity.noContent().build();
    }

}
