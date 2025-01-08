package com.example.learningplateform.repositories;

import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByNomCurso(String nombre);
    List<Course> findAllByInstructor(Instructor instructor);
}
