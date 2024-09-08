package com.clinica.odontologia.service;

import com.clinica.odontologia.model.Odontologo;

import java.util.List;
import java.util.Optional;

public interface OdontologoService {
    Odontologo guardarOdontologo(Odontologo odontologo);
    List<Odontologo> listarTodos();
    Optional<Odontologo> buscarPorId(Long id);
    void eliminarOdontologo(Long id);
    List<Odontologo> buscarPorNombre(String nombre);
    Optional<Odontologo> buscarPorMatricula(String matricula);

    List<Odontologo> buscarPorApellido(String apellido);
}
