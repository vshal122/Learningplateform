package com.example.learningplateform.dto;

import com.example.learningplateform.model.Instructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseDto {
    private String nomCurso;
    private String descCurso;
    private String dificultad;
    private String detalle;
    private String url;
    private String imgurl;
    private Instructor instructor;
}
