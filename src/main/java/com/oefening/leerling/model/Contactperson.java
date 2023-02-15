package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Contactperson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = null;
    private String name;
    private String phone_number;
    @OneToOne
    @JsonBackReference
    @JoinColumn(name = "student_id")
    private Student student;

    public Contactperson() {
    }

    public Contactperson(String name, String phone_number) {
        this.name = name;
        this.phone_number = phone_number;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
