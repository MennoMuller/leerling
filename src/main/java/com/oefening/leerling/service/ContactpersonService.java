package com.oefening.leerling.service;

import com.oefening.leerling.model.Contactperson;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.repository.ContactpersonRepository;
import com.oefening.leerling.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ContactpersonService {
    @Autowired
    ContactpersonRepository contactpersonRepository;
    @Autowired
    StudentRepository studentRepository;

    //CREATE
    public Contactperson newContact(Student student, Contactperson contactperson) {
        contactperson.setStudent(student);
        return contactpersonRepository.save(contactperson);
    }

    //READ
    public Optional<Contactperson> getContactById(long id) {
        return contactpersonRepository.findById(id);
    }

    public Iterable<Contactperson> getAllContacts() {
        return contactpersonRepository.findAll();
    }

    //UPDATE
    public Contactperson updateContactById(long id, Contactperson contactperson) {
        if (!contactpersonRepository.existsById(id)) {
            return null;
        }
        Contactperson oldContact = contactpersonRepository.findById(id).get();
        if (contactperson.getName() != null) {
            oldContact.setName(contactperson.getName());
        }
        if (contactperson.getPhone_number() != null) {
            oldContact.setPhone_number(contactperson.getPhone_number());
        }

        return contactpersonRepository.save(oldContact);
    }

    public Contactperson addStudentToContact(long id, Student student) {
        if (!contactpersonRepository.existsById(id)) {
            return null;
        }
        Contactperson contactperson = contactpersonRepository.findById(id).get();
        if (contactperson.getStudent() != null) {
            Student oldStudent = contactperson.getStudent();
            oldStudent.setContactperson(null);
            studentRepository.save(oldStudent);
        }
        contactperson.setStudent(student);
        return contactpersonRepository.save(contactperson);
    }

    //DELETE
    public String deleteContactById(long id) {
        if (!contactpersonRepository.existsById(id)) {
            return "Error: no such contact";
        }
        contactpersonRepository.deleteById(id);
        return "Successfully deleted contact";
    }

    public void deleteAllContacts() {
        contactpersonRepository.deleteAll();
    }
}
