package com.example.learningplateform.controller;

import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import com.example.learningplateform.services.core.impl.CourseService;
import com.example.learningplateform.services.core.impl.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class APIController {

    private InstructorService instructorService;
    private CourseService courseService;

    @Autowired
    public APIController(InstructorService profesorService, CourseService cursoService) {
        super();
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/profesores")
    public List<Instructor> getAllProf() {
        return this.instructorService.getAll();
    }

    @GetMapping("/cursos")
    public List<Course> getAllCurso() {
        return this.courseService.getAll();
    }
}
