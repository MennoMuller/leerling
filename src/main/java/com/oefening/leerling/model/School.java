package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
