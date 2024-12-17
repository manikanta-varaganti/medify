package com.medify.medifybackend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medify.medifybackend.enums.Gender;
import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "patients")
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User {
    @OneToOne
    @JoinColumn(name = "user_id") // References the User table's ID
    private User user;

    @OneToMany(mappedBy = "patient")
    private List<Appointment> appointments;

    private Integer age;
    private String address;

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
