package com.oefening.leerling.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
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
