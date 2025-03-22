package com.likith.collablearn.service;

import java.util.List;
import java.util.Optional;

import com.likith.collablearn.entity.Course;

public interface CoursesService {
    Course saveCourse(Course course);
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    List<Course> getCoursesByInstructor(String instructor);
    Course updateCourse(Long id, Course course);
    void deleteCourse(Long id);
}
