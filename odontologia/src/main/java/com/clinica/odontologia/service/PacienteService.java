package com.clinica.odontologia.service;

import com.clinica.odontologia.model.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    Paciente guardarPaciente(Paciente paciente);
    List<Paciente> listarTodos();
    Optional<Paciente> buscarPorId(Long id);
    void eliminarPaciente(Long id);
}
