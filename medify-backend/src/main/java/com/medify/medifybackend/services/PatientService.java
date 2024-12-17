package com.medify.medifybackend.services;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.medify.medifybackend.models.Patient;
import com.medify.medifybackend.repositories.PatientRepository;
import com.medify.medifybackend.utils.Patcher;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    Patcher patcher;

    public Iterable<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    public Patient addPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Patient existingPatient, Patient newPatient, Long id) throws JsonMappingException {
        //update all fields by mapping newPatient to existingPatient
        try {
            //send both existingPatient and  newPatient to patientPatcher
            patcher.patientPatcher(existingPatient, newPatient);
            //save the updated patient to repository
            return  patientRepository.save(existingPatient);
        }catch (Exception e) {
            throw new RuntimeException("Error updating the Patient: ", e);
        }

    }

    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
