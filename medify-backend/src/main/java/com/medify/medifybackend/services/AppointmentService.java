package com.medify.medifybackend.services;

import com.medify.medifybackend.enums.AppointmentStatus;
import com.medify.medifybackend.models.Appointment;
import com.medify.medifybackend.models.Doctor;
import com.medify.medifybackend.models.Patient;
import com.medify.medifybackend.repositories.AppointmentRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    public Iterable<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<Appointment> getAppointment(Long id) {
        return appointmentRepository.findById(id);
    }


    // Method to find appointments by Doctor
    public Iterable<Appointment> getAppointmentsByDoctor(Doctor doctor) {
        return appointmentRepository.findByDoctor(doctor);
    }

    // Method to find appointments by Patient
    public Iterable<Appointment> getAppointmentsByPatient(Patient patient) {
        return appointmentRepository.findByPatient(patient);
    }

    @Transactional
    public Appointment addAppointment(Appointment appointment) {

        appointment.setStatus(AppointmentStatus.PENDING_APPROVAL);

        return appointmentRepository.save(appointment);
    }

    @Transactional
    public Appointment updateAppointment(Long id, Appointment newAppointment) {
        newAppointment.setId(id);
        return entityManager.merge(newAppointment);
    }

    public Appointment cancelAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        return appointmentRepository.save(appointment);
    }

    public Appointment declineAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.DECLINED);
        return appointmentRepository.save(appointment);
    }

    public Appointment acceptAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.ACCEPTED);
        return appointmentRepository.save(appointment);
    }

    public Appointment noShowAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.NO_SHOW);
        return appointmentRepository.save(appointment);
    }

    public Appointment done(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.DONE);
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }

}
