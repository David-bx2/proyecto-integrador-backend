package com.clinica.odontologia.service;

import com.clinica.odontologia.model.Turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Turno guardarTurno(Turno turno);
    List<Turno> listarTodos();
    Optional<Turno> buscarPorId(Long id);
    void eliminarTurno(Long id);

    List<Turno> listarTurnosPorOdontologo(Long odontologoId);
}
