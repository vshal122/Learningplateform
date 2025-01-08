package com.example.learningplateform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profesor")
public class Instructor {

    @Id
    @Column(name = "profesor_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_profesor;
    @Column(name = "nombre")
    private String nomProfesor;
    @Column(name = "apellido")
    private String apeProfesor;
    @Column(name = "correo")
    private String correoProfesor;
    @Column(name = "descripcion")
    private String descProfesor;
    @Column(name = "detalle")
    private String detalleProfesor;
    private String imgurl;


    public Instructor(String nomProfesor, String apeProfesor, String correoProfesor, String descProfesor, String imgurl) {
        this.nomProfesor = nomProfesor;
        this.apeProfesor = apeProfesor;
        this.correoProfesor = correoProfesor;
        this.descProfesor = descProfesor;
        this.imgurl = imgurl;
    }

    public Instructor(String detalleProfesor) {
        this.detalleProfesor = detalleProfesor;
    }
}
