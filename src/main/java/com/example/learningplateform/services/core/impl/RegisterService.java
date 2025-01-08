package com.example.learningplateform.services.core.impl;

import com.example.learningplateform.auth.User;
import com.example.learningplateform.auth.UserRepository;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Register;
import com.example.learningplateform.repositories.CourseRepository;
import com.example.learningplateform.repositories.RegisterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RegisterService {

    private RegisterRepository matriculaRepository;
    private CourseRepository cursoRepository;
    private UserRepository userRepository;

    @Autowired
    public RegisterService(RegisterRepository matriculaRepository, CourseRepository cursoRepository, UserRepository userRepository) {
        this.matriculaRepository = matriculaRepository;
        this.cursoRepository = cursoRepository;
        this.userRepository = userRepository;
    }

    public void createMatricula(Long id_curso, String username) throws Exception {
        Course curso = cursoRepository.findById(id_curso).get();
        User user = userRepository.findByUsername(username);

        if (null != matriculaRepository.findByCursoAndUsuario(curso, user)) {
            throw new Exception("Ya se encuentra matriculado en este curso");
        }
        LocalDate date = LocalDate.now();
        Register register = new Register(date, user, curso);
        matriculaRepository.save(register);
    }
}
