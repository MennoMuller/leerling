package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
}
