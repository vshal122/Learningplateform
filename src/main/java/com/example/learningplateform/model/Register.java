package com.example.learningplateform.model;
import com.example.learningplateform.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "matricula")
public class Register {
    @Id
    @Column(name = "matricula_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_matricula;

    @Column(name = "fecha")
    private LocalDate fecha_matricula;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User usuario;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "curso_id", nullable = false)
    private Course curso;

    public Register(LocalDate fecha_matricula, User usuario, Course curso) {
        this.fecha_matricula = fecha_matricula;
        this.usuario = usuario;
        this.curso = curso;
    }
}
