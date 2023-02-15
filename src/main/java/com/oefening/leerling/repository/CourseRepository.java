package com.oefening.leerling.repository;

import com.oefening.leerling.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface CourseRepository extends CrudRepository<Course, Long> {
}
