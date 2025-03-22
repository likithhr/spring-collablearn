package com.likith.collablearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.likith.collablearn.entity.Course;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    
    // Custom query methods
    List<Course> findByInstructor(String instructor);
    
    List<Course> findByTitleContainingIgnoreCase(String keyword);
}
