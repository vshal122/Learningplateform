package com.example.learningplateform.services.core.impl;

import com.example.learningplateform.dto.InstructorDto;
import com.example.learningplateform.model.Instructor;
import com.example.learningplateform.repositories.InstructorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstructorService {

    private InstructorRepository instructorRepository;

    @Autowired
    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }

    public void create(InstructorDto profesotDto) {
        String nombre = profesotDto.getNombre();
        String apellido = profesotDto.getApellido();
        String correo = profesotDto.getCorreo();
        String descripcion = profesotDto.getDescripcion();
        String imgurl = profesotDto.getImgurl();
        Instructor profesor = new Instructor(nombre, apellido, correo, descripcion, imgurl);

        instructorRepository.save(profesor);
    }

    public List<Instructor> getAll() {
        return instructorRepository.findAll();
    }

    public void update(Instructor profesor) {
        Instructor currentProfesor = instructorRepository.findById(profesor.getId_profesor()).get();

        currentProfesor.setNomProfesor(profesor.getNomProfesor());
        currentProfesor.setApeProfesor(profesor.getApeProfesor());
        currentProfesor.setCorreoProfesor(profesor.getCorreoProfesor());
        currentProfesor.setDescProfesor(profesor.getDescProfesor());
        currentProfesor.setImgurl(profesor.getImgurl());

        instructorRepository.save(currentProfesor);
    }

    public void patch(Instructor profesor) {
        Instructor current = instructorRepository.findById(profesor.getId_profesor()).get();

        current.setDetalleProfesor(profesor.getDetalleProfesor());

        instructorRepository.save(current);
    }

    public void delete(Instructor profesor) {
        instructorRepository.delete(profesor);
    }

}
