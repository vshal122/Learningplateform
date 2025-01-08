package com.example.learningplateform.controller;

import com.example.learningplateform.dto.InstructorDto;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import com.example.learningplateform.repositories.CourseRepository;
import com.example.learningplateform.repositories.InstructorRepository;
import com.example.learningplateform.services.core.impl.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/profesores")
public class InstructorController {

    private InstructorService profesorService;
    private InstructorRepository profesorRepository;
    private CourseRepository cursoRepository;

    @Autowired
    public InstructorController(InstructorService profesorService, InstructorRepository profesorRepository,
                                CourseRepository cursoRepository) {
        this.profesorService = profesorService;
        this.profesorRepository = profesorRepository;
        this.cursoRepository = cursoRepository;
    }

    @GetMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String addProfesor(Model model) {
        model.addAttribute("profesor", new InstructorDto());
        return "instructor/instructor-add";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String saveProfesor(InstructorDto profesor) {
        profesorService.create(profesor);

        return "redirect:/instructor";
    }

    @GetMapping("/edit/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getProfesorForUpdate(@PathVariable Long id_profesor,
                                       Model model) {
        try {
            Instructor profesorActual = profesorRepository.findById(id_profesor).get();
            model.addAttribute("profesor", profesorActual);
            return "instructor/instructor-edit";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/update/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateProfesor(@PathVariable Long id_profesor,
                                 Instructor profesor, RedirectAttributes attributes, Model model){

        try {
            Instructor currentProfesor = profesorRepository.findById(id_profesor).get();
            currentProfesor.setNomProfesor(profesor.getNomProfesor());
            currentProfesor.setApeProfesor(profesor.getApeProfesor());
            currentProfesor.setCorreoProfesor(profesor.getCorreoProfesor());
            currentProfesor.setDescProfesor(profesor.getDescProfesor());
            currentProfesor.setImgurl(profesor.getImgurl());

            profesorService.update(profesor);
            attributes.addAttribute("id_profesor", id_profesor);

            return "redirect:/instructor/{id_profesor}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @PostMapping("/patch/{id_profesor}")
    public String patchProfesor(@PathVariable Long id_profesor, Instructor profesor, RedirectAttributes attributes, Model model) {

        try {
            Instructor current = profesorRepository.findById(id_profesor).get();
            current.setDetalleProfesor(profesor.getDetalleProfesor());
            profesorService.patch(current);

            attributes.addAttribute("id_profesor", id_profesor);
            return "redirect:/instructor/{id_profesor}";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getProfesoresList(Model model) {
        List<Instructor> profesores = profesorService.getAll();
        model.addAttribute("profesores", profesores);
        return "instructor/instructor";
    }

    @GetMapping("/delete/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteProfesor(@PathVariable Long id_profesor, Model model) {
        try {
            Instructor profesorActual = profesorRepository.findById(id_profesor).get();
            profesorService.delete(profesorActual);

            return "redirect:/instructor";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }

    @GetMapping("/{id_profesor}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getProfesorDetail(@PathVariable Long id_profesor, Model model) {
        try {
            Instructor profesor = profesorRepository.findById(id_profesor).get();
            model.addAttribute("profesor", profesor);
            List<Course> cursos = cursoRepository.findAllByInstructor(profesor);
            model.addAttribute("cursos", cursos);
            return "instructor/instructor-detail";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e);
            return "error";
        }
    }
}
