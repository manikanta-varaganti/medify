package com.medify.medifybackend.services;

import com.medify.medifybackend.models.Doctor;
import com.medify.medifybackend.repositories.DoctorRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private EntityManager entityManager;

    public Iterable<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }

    public Optional<Doctor> getDoctorById(Long id){
        return doctorRepository.findById(id);
    }

    @Transactional
    public Doctor addDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor newDoctor){
        newDoctor.setId(id);
        return entityManager.merge(newDoctor);
    }

    public void deleteDoctorById(Long id){
        doctorRepository.deleteById(id);
    }
}
