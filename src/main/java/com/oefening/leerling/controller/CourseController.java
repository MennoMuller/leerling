package com.oefening.leerling.controller;

import com.oefening.leerling.model.Course;
import com.oefening.leerling.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    //CREATE
    @PostMapping("/new")
    public Course newCourse(@RequestBody Course course) {
        return courseService.newCourse(course);
    }

    //READ
    @GetMapping("/{id}")
    public Optional<Course> getCourseById(@PathVariable(value = "id") long id) {
        return courseService.getCourseById(id);
    }

    @GetMapping("/all")
    public Iterable<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    //UPDATE
    @PutMapping("/{id}")
    public Course updateCourseById(@PathVariable(value = "id") long id, @RequestBody Course course) {
        return courseService.updateCourseById(id, course);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public String deleteCourseById(@PathVariable(value = "id") long id) {
        return courseService.deleteCourseById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCourses() {
        courseService.deleteAllCourses();
    }
}
