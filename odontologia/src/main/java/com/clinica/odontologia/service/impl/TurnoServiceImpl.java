package com.clinica.odontologia.service.impl;

import com.clinica.odontologia.exception.ResourceNotFoundException;
import com.clinica.odontologia.model.Turno;
import com.clinica.odontologia.repository.TurnoRepository;
import com.clinica.odontologia.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    @Override
    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    @Override
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (!turno.isPresent()) {
            throw new ResourceNotFoundException("Turno con ID " + id + " no encontrado.");
        }
        return turnoRepository.findById(id);
    }

    @Override
    public void eliminarTurno(Long id) {
        Optional<Turno> turno = turnoRepository.findById(id);
        if (!turno.isPresent()) {
            throw new ResourceNotFoundException("Turno con ID " + id + " no encontrado.");
        }
        turnoRepository.deleteById(id);
    }

    @Override
    public List<Turno> listarTurnosPorOdontologo(Long odontologoId) {
        return turnoRepository.findByOdontologo_Id(odontologoId);
    }

}
