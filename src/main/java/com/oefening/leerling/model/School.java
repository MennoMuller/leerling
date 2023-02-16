package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class School {
    @OneToMany(mappedBy = "school")
    @JsonBackReference
    private final List<Student> students = new ArrayList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private final Long id = null;
    private String name;
    private Integer amount_of_students = 0;
}
