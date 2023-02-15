package com.oefening.leerling.service;

import com.oefening.leerling.model.Contactperson;
import com.oefening.leerling.model.Course;
import com.oefening.leerling.model.School;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.repository.SchoolRepository;
import com.oefening.leerling.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    ContactpersonService contactpersonService;
    @Autowired
    CourseService courseService;


    //CREATE

    public Student newStudent(Student student, School school) {
        student.setSchool(school);
        return studentRepository.save(student);
    }

    //READ

    public Optional<Student> getStudentById(long id) {
        return studentRepository.findById(id);
    }

    public Iterable<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Iterable<Student> getStudentsByName(String query) {
        return studentRepository.findByNameContaining(query);
    }

    //UPDATE

    public Student updateStudentById(long id, Student student) {
        if (!studentRepository.existsById(id)) {
            return null;
        }
        Student oldStudent = studentRepository.findById(id).get();

        if (student.getName() != null) {
            oldStudent.setName(student.getName());
        }
        if (student.getGrade() != null) {
            oldStudent.setGrade(student.getGrade());
        }
        if (student.getSchool() != null) {
            oldStudent.setSchool(student.getSchool());
        }

        return studentRepository.save(oldStudent);
    }

    public Student addContactToStudent(long id, Contactperson contactperson) {
        if (!studentRepository.existsById(id)) {
            return null;
        }
        Student student = studentRepository.findById(id).get();

        Contactperson tempContactperson = contactpersonService.newContact(student, contactperson);
        student.setContactperson(tempContactperson);
        return studentRepository.save(student);
    }

    public Student addContactById(long studentid, long contactid) {
        if (!studentRepository.existsById(studentid)) {
            return null;
        }
        Student student = studentRepository.findById(studentid).get();
        if (contactpersonService.getContactById(contactid).isPresent()) {
            if (student.getContactperson() != null) {
                Contactperson oldContact = student.getContactperson();
                contactpersonService.addStudentToContact(oldContact.getId(), null);
            }
            Contactperson contactperson = contactpersonService.addStudentToContact(contactid, student);
            student.setContactperson(contactperson);
        }
        return studentRepository.save(student);
    }

    public Student addCourseById(long studentid, long courseid) {
        if (!studentRepository.existsById(studentid)) {
            return null;
        }
        Student student = studentRepository.findById(studentid).get();
        if (courseService.getCourseById(courseid).isPresent()) {
            Course course = courseService.addStudentToCourse(courseid, student);
            student.getCourses().add(course);
        }
        return studentRepository.save(student);
    }

    public Student addSchoolToStudent(long id, School school) {
        if (!studentRepository.existsById(id)) {
            return null;
        }
        Student student = studentRepository.findById(id).get();
        student.setSchool(school);
        return studentRepository.save(student);
    }

    public Student removeCourseById(long studentid, long courseid) {
        if (!studentRepository.existsById(studentid)) {
            return null;
        }
        Student student = studentRepository.findById(studentid).get();
        if (courseService.getCourseById(courseid).isPresent()) {
            Course course = courseService.removeStudentFromCourse(courseid, student);
            student.getCourses().remove(course);
        }
        return studentRepository.save(student);
    }
    //DELETE

    public String deleteStudentById(long id) {
        if (!studentRepository.existsById(id)) {
            return "error: no such student exists";
        }
        long schoolid = studentRepository.findById(id).get().getSchool().getId();
        System.out.println(schoolid);
        studentRepository.deleteById(id);
        School school = schoolRepository.findById(schoolid).get();
        System.out.println(school.getStudents());
        school.setAmount_of_students(school.getAmount_of_students() - 1);
        schoolRepository.save(school);
        return "Successfully deleted student";
    }

    public void deleteAllStudents() {
        studentRepository.deleteAll();
        Iterable<School> schools = schoolRepository.findAll();
        for (School school : schools) {
            school.setAmount_of_students(0);
        }
        schoolRepository.saveAll(schools);
    }


}
