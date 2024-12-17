package com.medify.medifybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medify.medifybackend.enums.Speciality;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "doctors")
@PrimaryKeyJoinColumn(name = "id")
public class Doctor extends User {
    @OneToOne
    @JoinColumn(name = "user_id") // References the User table's ID
    private User user;

    @OneToMany(mappedBy = "doctor")
    private List<Appointment> appointments;

    private Integer yearsOfExperience;
    private Speciality speciality;
    private String address;
    private String fee;
    private String reviews;

    public Integer getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(Integer yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }
}
