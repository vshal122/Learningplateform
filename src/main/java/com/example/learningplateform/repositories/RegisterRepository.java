package com.example.learningplateform.repositories;

import com.example.learningplateform.auth.User;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findAllByCurso(Course curso);
    List<Register> findAllByUsuario(User user);
    Register findByCursoAndUsuario(Course curso, User user);
}
