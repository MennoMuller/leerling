package com.oefening.leerling.repository;

import com.oefening.leerling.model.School;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SchoolRepository extends CrudRepository<School, Long> {
}
