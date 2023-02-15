package com.oefening.leerling.service;

import com.oefening.leerling.model.School;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SchoolService {
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    StudentService studentService;

    //CREATE

    public School addSchool(School school) {
        return schoolRepository.save(school);
    }

    //READ

    public Optional<School> getSchoolById(long id) {
        return schoolRepository.findById(id);
    }

    public Iterable<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    //UPDATE

    public School updateSchoolById(long id, School school) {
        if (!schoolRepository.existsById(id)) {
            return null;
        }
        School oldSchool = schoolRepository.findById(id).get();
        if (school.getName() != null) {
            oldSchool.setName(school.getName());
        }
        return schoolRepository.save(oldSchool);
    }

    public School addStudentToSchool(long id, Student student) {
        if (!schoolRepository.existsById(id)) {
            return null;
        }
        School school = schoolRepository.findById(id).get();
        Student tempStudent = studentService.newStudent(student, school);

        school.getStudents().add(tempStudent);
        school.setAmount_of_students(school.getStudents().size());

        return schoolRepository.save(school);
    }

    public School addStudentById(long schoolid, long studentid) {
        Optional<School> foundSchool = schoolRepository.findById(schoolid);
        if (foundSchool.isEmpty()) {
            return null;
        }
        School school = foundSchool.get();
        if (studentService.getStudentById(studentid).isPresent()) {
            Student student = studentService.addSchoolToStudent(studentid, school);
            school.getStudents().add(student);
            school.setAmount_of_students(school.getStudents().size());
        }
        return schoolRepository.save(school);
    }

    public School removeStudentFromSchool(long id, Student student) {
        if (!schoolRepository.existsById(id)) {
            return null;
        }
        School school = schoolRepository.findById(id).get();
        school.getStudents().remove(student);
        school.setAmount_of_students(school.getStudents().size());
        return schoolRepository.save(school);
    }

    //DELETE

    public String deleteSchoolById(long id) {
        if (!schoolRepository.existsById(id)) {
            return "No such school exists";
        }
        schoolRepository.deleteById(id);
        return "Successfully deleted school";
    }

    public void deleteAllSchools() {
        schoolRepository.deleteAll();
    }
}
