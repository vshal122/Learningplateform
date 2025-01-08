package com.example.learningplateform.services.core.impl;

import com.example.learningplateform.dto.CourseDto;
import com.example.learningplateform.model.Course;
import com.example.learningplateform.model.Instructor;
import com.example.learningplateform.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseService {


    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void create(CourseDto courseDto) throws Exception{
        if (null != courseRepository.findByNomCurso(courseDto.getNomCurso())) {
            throw new Exception("A course with the name  already exists " + courseDto.getNomCurso());
        }
        String nomCurso = courseDto.getNomCurso();
        String descCurso = courseDto.getDescCurso();
        String detalleCurso = courseDto.getDetalle();
        String difCurso = courseDto.getDificultad();
        String urlCurso = courseDto.getUrl();
        String imgurl = courseDto.getImgurl();
        Instructor instructor = courseDto.getInstructor();
        Course course = new Course(nomCurso, descCurso, detalleCurso, difCurso, urlCurso, imgurl, instructor);

        courseRepository.save(course);
    }

    public void update(Course curso, Long id_curso) {
        Course currentCurso = courseRepository.findById(id_curso).get();

            currentCurso.setNomCurso(curso.getNomCurso());
            currentCurso.setDescripcionCurso(curso.getDescripcionCurso());
            currentCurso.setDetalleCurso(curso.getDetalleCurso());
            currentCurso.setDificultadCurso(curso.getDificultadCurso());
            currentCurso.setUrlCurso(curso.getUrlCurso());
            currentCurso.setImgurl(curso.getImgurl());
            currentCurso.setInstructor(curso.getInstructor());

        courseRepository.save(currentCurso);

    }

    public void delete(Course curso) { courseRepository.delete(curso); }


    public List<Course> getAll() {
        return courseRepository.findAll();
    }

}
