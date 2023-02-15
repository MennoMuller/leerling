package com.oefening.leerling.service;

import com.oefening.leerling.model.Course;
import com.oefening.leerling.model.Student;
import com.oefening.leerling.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    //CREATE
    public Course newCourse(Course course) {
        return courseRepository.save(course);
    }

    //READ
    public Optional<Course> getCourseById(long id) {
        return courseRepository.findById(id);
    }

    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    //UPDATE

    public Course updateCourseById(long id, Course course) {
        if (!courseRepository.existsById(id)) {
            return null;
        }
        Course oldCourse = courseRepository.findById(id).get();
        if (course.getName() != null) {
            oldCourse.setName(course.getName());
        }
        if (course.getCredits() != null) {
            oldCourse.setCredits(course.getCredits());
        }
        return courseRepository.save(oldCourse);
    }

    public Course addStudentToCourse(long id, Student student) {
        if (!courseRepository.existsById(id)) {
            return null;
        }
        Course course = courseRepository.findById(id).get();
        course.getStudents().add(student);
        return courseRepository.save(course);
    }

    public Course removeStudentFromCourse(long id, Student student) {
        if (!courseRepository.existsById(id)) {
            return null;
        }
        Course course = courseRepository.findById(id).get();
        course.getStudents().remove(student);
        return courseRepository.save(course);
    }

    //DELETE
    public String deleteCourseById(long id) {
        if (!courseRepository.existsById(id)) {
            return "Error: no such course exists";
        }
        courseRepository.deleteById(id);
        return "Successfully deleted course";
    }

    public void deleteAllCourses() {
        courseRepository.deleteAll();
    }


}
