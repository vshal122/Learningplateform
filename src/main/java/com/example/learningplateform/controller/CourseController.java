package com.example.learningplateform.controller;


import com.example.learningplateform.auth.User;
import com.example.learningplateform.auth.UserRepository;
import com.example.learningplateform.dto.CourseDto;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import com.example.learningplateform.repositories.InstructorRepository;
import com.example.learningplateform.repositories.RegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.learningplateform.services.core.impl.CourseService;
import com.example.learningplateform.repositories.CourseRepository;

import java.util.List;

@Controller
@RequestMapping("/cursos")
public class CourseController {

    private CourseService courseService;
    private CourseRepository cursoRepository;
    private RegisterRepository matriculaRepository;
    private UserRepository userRepository;
    private InstructorRepository  profesorRepository;

    @Autowired
    public CourseController(CourseService cursoService, CourseRepository cursoRepository,
                            RegisterRepository matriculaRepository, UserRepository userRepository, InstructorRepository profesorRepository) {
        super();
        this.courseService = cursoService;
        this.cursoRepository = cursoRepository;
        this.matriculaRepository = matriculaRepository;
        this.userRepository = userRepository;
        this.profesorRepository = profesorRepository;
    }

    @GetMapping("/add/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCurso(@PathVariable Long id_profesor, Model model) {
        try {
            Instructor current = profesorRepository.findById(id_profesor).get();
            model.addAttribute("curso", new CourseDto());
            model.addAttribute("profesor", current);
            return "cursos/curso-add";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/add/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCurso(@PathVariable Long id_profesor, CourseDto curso, Model model) {
        try {
            Instructor current = profesorRepository.findById(id_profesor).get();
            curso.setInstructor(current);
            courseService.create(curso);
            return "redirect:/cursos";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }

    }

    @GetMapping("/edit/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getCursoForUpdate(@PathVariable Long id_curso, Model model) {
        try {
            Course cursoActual = cursoRepository.findById(id_curso).get();
            model.addAttribute("curso", cursoActual);
            return "cursos/curso-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/edit/{id_profesor}/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateCurso(@PathVariable Long id_profesor, @PathVariable Long id_curso, Course curso, Model model, RedirectAttributes attributes) {

        try {
            Instructor currentProfesor = profesorRepository.findById(id_profesor).get();
            curso.setInstructor(currentProfesor);

            courseService.update(curso, id_curso);
            attributes.addAttribute("id_curso", id_curso);

            return "redirect:/cursos/{id_curso}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    public String getCursosList(Model model) {
        List<Course> cursos = courseService.getAll();
        model.addAttribute("cursos", cursos);
        return "cursos/cursos";
    }

    @GetMapping("/delete/{id_curso}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCurso(@PathVariable Long id_curso, Model model) {
        try {
            Course cursoActual = cursoRepository.findById(id_curso).get();
            courseService.delete(cursoActual);

            return "redirect:/cursos";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_curso}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getCursoDetail(@PathVariable Long id_curso, Authentication authentication, Model model) {
        String username = authentication.getName();
        Boolean matriculado = false;
        try {
            Course curso = cursoRepository.findById(id_curso).get();
            User user = userRepository.findByUsername(username);
            if (null != matriculaRepository.findByCursoAndUsuario(curso, user)) {
                matriculado = true;
            }
            model.addAttribute("curso", curso);
            model.addAttribute("matriculado", matriculado);
            return "cursos/curso-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
