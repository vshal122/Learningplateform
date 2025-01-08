package com.example.learningplateform.repositories;

import com.example.learningplateform.auth.User;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Long> {

    List<Register> findAllByCurso(Course curso);
    List<Register> findAllByUsuario(User user);
    Register findByCursoAndUsuario(Course curso, User user);
}
