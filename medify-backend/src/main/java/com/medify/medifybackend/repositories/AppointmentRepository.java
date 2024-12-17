package com.medify.medifybackend.repositories;

import com.medify.medifybackend.models.Appointment;
import com.medify.medifybackend.models.Doctor;
import com.medify.medifybackend.models.Patient;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;


public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    Iterable<Appointment> findByDoctor(Doctor doctor);
    Iterable<Appointment> findByPatient(Patient patient);
    boolean existsByDoctorAndDateTime(Doctor doctor, LocalDateTime dateTime);

}
