package com.oefening.leerling.repository;

import com.oefening.leerling.model.Contactperson;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ContactpersonRepository extends CrudRepository<Contactperson, Long> {
}
