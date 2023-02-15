package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class School {
    @OneToMany(mappedBy = "school")
    @JsonBackReference
    private final List<Student> students = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = null;
    private String name;
    private Integer amount_of_students = 0;

    public School() {
    }

    public School(String name) {
        this.name = name;
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

    public Integer getAmount_of_students() {
        return amount_of_students;
    }

    public void setAmount_of_students(Integer amount_of_students) {
        this.amount_of_students = amount_of_students;
    }

    public List<Student> getStudents() {
        return students;
    }
}
