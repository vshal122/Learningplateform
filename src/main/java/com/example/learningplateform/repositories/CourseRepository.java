package com.example.learningplateform.repositories;

import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findByNomCurso(String nombre);
    List<Course> findAllByInstructor(Instructor instructor);
}
