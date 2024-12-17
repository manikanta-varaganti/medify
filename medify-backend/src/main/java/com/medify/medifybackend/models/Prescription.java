package com.medify.medifybackend.models;

import jakarta.persistence.*;

@Entity
@Table(name="prescriptions")
public class Prescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String symptoms;
    private String disease;
    private String medicines;
    private boolean followUp;

    @OneToOne(mappedBy = "prescription") // Reference to the owning side
    private Appointment appointment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getMedicines() {
        return medicines;
    }

    public void setMedicines(String medicines) {
        this.medicines = medicines;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }
}
