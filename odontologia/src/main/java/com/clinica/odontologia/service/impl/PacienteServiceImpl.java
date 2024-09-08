package com.clinica.odontologia.service.impl;

import com.clinica.odontologia.exception.ResourceNotFoundException;
import com.clinica.odontologia.model.Paciente;
import com.clinica.odontologia.repository.PacienteRepository;
import com.clinica.odontologia.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Override
    public Paciente guardarPaciente(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> buscarPorId(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (!paciente.isPresent()) {
            throw new ResourceNotFoundException("Paciente con ID " + id + " no encontrado.");
        }
        return pacienteRepository.findById(id);
    }

    @Override
    public void eliminarPaciente(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        if (!paciente.isPresent()) {
            throw new ResourceNotFoundException("Paciente con ID " + id + " no encontrado.");
        }
        pacienteRepository.deleteById(id);
    }
}
