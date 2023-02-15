package com.oefening.leerling.controller;

import com.oefening.leerling.model.School;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/school")
public class SchoolController {
    @Autowired
    SchoolService schoolService;

    //CREATE

    @PostMapping("/new")
    public School addSchool(@RequestBody School school) {
        return schoolService.addSchool(school);
    }

    //READ

    @GetMapping("/{id}")
    public Optional<School> getSchoolById(@PathVariable(value = "id") long id) {
        return schoolService.getSchoolById(id);
    }

    @GetMapping("/all")
    public Iterable<School> getAllSchools() {
        return schoolService.getAllSchools();
    }

    //UPDATE

    @PutMapping("/{id}")
    public School updateSchoolById(@PathVariable(value = "id") long id, @RequestBody School school) {
        return schoolService.updateSchoolById(id, school);
    }

    @PutMapping("/{id}/student")
    public School addStudentToSchool(@PathVariable(value = "id") long id, @RequestBody Student student) {
        return schoolService.addStudentToSchool(id, student);
    }

    @PutMapping("/{schoolid}/student/{studentid}")
    public School addStudentById(@PathVariable(value = "schoolid") long schoolid, @PathVariable(value = "studentid") long studentid) {
        return schoolService.addStudentById(schoolid, studentid);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteSchoolById(@PathVariable(value = "id") long id) {
        return schoolService.deleteSchoolById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllSchools() {
        schoolService.deleteAllSchools();
    }
}
