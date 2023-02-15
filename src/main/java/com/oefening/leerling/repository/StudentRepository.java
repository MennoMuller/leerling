package com.oefening.leerling.repository;

import com.oefening.leerling.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface StudentRepository extends CrudRepository<Student, Long> {
    Iterable<Student> findByNameContaining(String query);
}
