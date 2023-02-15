package com.oefening.leerling.controller;

import com.oefening.leerling.model.Contactperson;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    //CREATE

    @PostMapping("/new")
    public Student newStudent(@RequestBody Student student) {
        return studentService.newStudent(student, null);
    }

    //READ

    @GetMapping("/{id}")
    public Optional<Student> getStudentById(@PathVariable(value = "id") long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/all")
    public Iterable<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/search/{query}")
    public Iterable<Student> getStudentsByName(@PathVariable(value = "query") String query) {
        return studentService.getStudentsByName(query);
    }

    //UPDATE

    @PutMapping("/{id}")
    public Student updateStudentById(@PathVariable(value = "id") long id, @RequestBody Student student) {
        return studentService.updateStudentById(id, student);
    }

    @PutMapping("/{id}/contact")
    public Student addContactToStudent(@PathVariable(value = "id") long id, @RequestBody Contactperson contactperson) {
        return studentService.addContactToStudent(id, contactperson);
    }

    @PutMapping("/{studentid}/contact/{contactid}")
    public Student addContactById(@PathVariable(value = "studentid") long studentid, @PathVariable(value = "contactid") long contactid) {
        return studentService.addContactById(studentid, contactid);
    }

    @PutMapping("/{studentid}/course/{courseid}")
    public Student addCourseById(@PathVariable(value = "studentid") long studentid, @PathVariable(value = "courseid") long courseid) {
        return studentService.addCourseById(studentid, courseid);
    }

    @PutMapping("/{studentid}/course/{courseid}/unenrol")
    public Student removeCourseById(@PathVariable(value = "studentid") long studentid, @PathVariable(value = "courseid") long courseid) {
        return studentService.removeCourseById(studentid, courseid);
    }

    //DELETE

    @DeleteMapping("/{id}")
    public String deleteStudentById(@PathVariable(value = "id") long id) {
        return studentService.deleteStudentById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllStudents() {
        studentService.deleteAllStudents();
    }

}
