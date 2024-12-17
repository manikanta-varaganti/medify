package com.medify.medifybackend.controllers;

import com.medify.medifybackend.enums.AppointmentStatus;
import com.medify.medifybackend.models.Appointment;
import com.medify.medifybackend.models.Doctor;
import com.medify.medifybackend.models.Patient;
import com.medify.medifybackend.repositories.AppointmentRepository;
import com.medify.medifybackend.services.AppointmentService;
import com.medify.medifybackend.services.DoctorService;
import com.medify.medifybackend.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public Iterable<Appointment> getAppointments() {
     return appointmentService.getAppointments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentService.getAppointment(id).orElseThrow(() ->new RuntimeException("Appointment Not found"));
        return ResponseEntity.ok(appointment);

    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestParam Long patientId, @RequestParam Long doctorId, @RequestBody Appointment appointment) {
        Optional<Patient> patientOptional = patientService.getPatientById(patientId);
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(doctorId);

        if(!patientOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }
        if(!doctorOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }



        Doctor doctor = doctorOptional.get();
        Patient patient = patientOptional.get();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);


        boolean appointmentExists = appointmentRepository.existsByDoctorAndDateTime(
                appointment.getDoctor(), appointment.getDateTime());

        if (appointmentExists) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Appointment savedAppointment = appointmentService.addAppointment(appointment);
        return ResponseEntity.ok(savedAppointment);

    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestParam Long patientId, @RequestParam Long doctorId, @RequestBody Appointment newAppointment) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        Optional<Patient> patientOptional = patientService.getPatientById(patientId);
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(doctorId);

        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }

        if(!patientOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }
        if(!doctorOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }

        Doctor doctor = doctorOptional.get();
        Patient patient = patientOptional.get();
        newAppointment.setPatient(patient);
        newAppointment.setDoctor(doctor);

        Appointment updatedAppointment = appointmentService.updateAppointment(id, newAppointment);
        return ResponseEntity.ok(updatedAppointment);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Appointment> cancelAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }

        Appointment appointment = appointmentOptional.get();
        Appointment cancelledAppointment = appointmentService.cancelAppointment(appointment);
        return ResponseEntity.ok(cancelledAppointment);
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<Appointment> acceptAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }

        Appointment appointment = appointmentOptional.get();
        Appointment acceptedAppointment = appointmentService.acceptAppointment(appointment);
        return ResponseEntity.ok(acceptedAppointment);
    }

    @PutMapping("/{id}/decline")
    public ResponseEntity<Appointment> declineAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }
        Appointment appointment = appointmentOptional.get();
        Appointment declinedAppointment = appointmentService.declineAppointment(appointment);
        return ResponseEntity.ok(declinedAppointment);
    }

    @PutMapping("/{id}/done")
    public ResponseEntity<Appointment> doneAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();

        }

        Appointment appointment = appointmentOptional.get();
        Appointment doneAppointment= appointmentService.done(appointment);
        return ResponseEntity.ok(doneAppointment);
    }

    @PutMapping("/{id}/noshow")
    public ResponseEntity<Appointment> noShowAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
            ResponseEntity.notFound().build();
        }
        Appointment appointment = appointmentOptional.get();
        Appointment noShowAppointment= appointmentService.noShowAppointment(appointment);
        return ResponseEntity.ok(noShowAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Appointment> deleteAppointment(@PathVariable Long id) {
        Optional<Appointment> appointmentOptional = appointmentService.getAppointment(id);
        if (!appointmentOptional.isPresent()) {
        ResponseEntity.notFound().build();
        }

        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }




}
