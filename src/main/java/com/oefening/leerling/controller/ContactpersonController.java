package com.oefening.leerling.controller;

import com.oefening.leerling.model.Contactperson;
import com.oefening.leerling.service.ContactpersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/contact")
public class ContactpersonController {
    @Autowired
    ContactpersonService contactpersonService;

    //CREATE
    @PostMapping("/new")
    public Contactperson newContact(@RequestBody Contactperson contactperson) {
        return contactpersonService.newContact(null, contactperson);
    }

    //READ
    @GetMapping("/{id}")
    public Optional<Contactperson> getContactById(@PathVariable(value = "id") long id) {
        return contactpersonService.getContactById(id);
    }

    @GetMapping("/all")
    public Iterable<Contactperson> getAllContacts() {
        return contactpersonService.getAllContacts();
    }

    //UPDATE
    @PutMapping("/{id}")
    public Contactperson updateContactById(@PathVariable(value = "id") long id, @RequestBody Contactperson contactperson) {
        return contactpersonService.updateContactById(id, contactperson);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteContactById(@PathVariable(value = "id") long id) {
        return contactpersonService.deleteContactById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllContacts() {
        contactpersonService.deleteAllContacts();
    }
}
