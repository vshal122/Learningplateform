package com.example.learningplateform.controller;

import com.example.learningplateform.auth.User;
import com.example.learningplateform.auth.UserRepository;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.repositories.CourseRepository;
import com.example.learningplateform.services.core.impl.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/matricula")
@PreAuthorize("hasRole('ROLE_USER')")
public class RegisterController {

    private RegisterService matriculaService;
    private UserRepository userRepository;
    private CourseRepository cursoRepository;

    @Autowired
    public RegisterController(RegisterService matriculaService, UserRepository userRepository, CourseRepository cursoRepository) {
        this.matriculaService = matriculaService;
        this.userRepository = userRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/save/{id_curso}")
    public String saveMatricula(@PathVariable Long id_curso, Authentication authentication, Model model) {
        try {
            String username = authentication.getName();
            matriculaService.createMatricula(id_curso, username);
            User user = userRepository.findByUsername(username);
            Course curso = cursoRepository.findById(id_curso).get();
            model.addAttribute("curso", curso);
            model.addAttribute("user", user);
            return "matricula-success";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
