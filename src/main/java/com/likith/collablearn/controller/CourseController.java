package com.likith.collablearn.controller;

import com.likith.collablearn.entity.Course;
import com.likith.collablearn.service.CoursesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CoursesService coursesService;

    
    @PostMapping("/create")
    public ResponseEntity<Course> createCourse(@RequestBody Course course) {
        Course savedCourse = coursesService.saveCourse(course);
        return ResponseEntity.ok(savedCourse);
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = coursesService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        Optional<Course> course = coursesService.getCourseById(id);
        return course.map(ResponseEntity::ok)
                     .orElse(ResponseEntity.notFound().build());
    }

   
    @GetMapping("/{instructor}")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@PathVariable String instructor) {
        List<Course> courses = coursesService.getCoursesByInstructor(instructor);
        return ResponseEntity.ok(courses);
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course updatedCourse) {
        try {
            Course course = coursesService.updateCourse(id, updatedCourse);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

   
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
    try {
        coursesService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    } catch (RuntimeException e) {
        return ResponseEntity.notFound().build();
    }
}
@GetMapping("/test")
public String testApi() {
    return "API is working!";
}


}
