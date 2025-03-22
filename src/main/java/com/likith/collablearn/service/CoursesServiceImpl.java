package com.likith.collablearn.service;

import com.likith.collablearn.entity.Course;
import com.likith.collablearn.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CoursesServiceImpl implements CoursesService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getCoursesByInstructor(String instructor) {
        return courseRepository.findByInstructor(instructor);
    }

    @Override
    public Course updateCourse(Long id, Course updatedCourse) {
        return courseRepository.findById(id).map(course -> {
            course.setTitle(updatedCourse.getTitle());
            course.setDescription(updatedCourse.getDescription());
            course.setPrice(updatedCourse.getPrice());
            course.setInstructor(updatedCourse.getInstructor());
            return courseRepository.save(course);
        }).orElseThrow(() -> new RuntimeException("Course not found with ID: " + id));
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
