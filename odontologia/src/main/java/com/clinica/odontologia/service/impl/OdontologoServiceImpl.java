package com.clinica.odontologia.service.impl;

import com.clinica.odontologia.exception.ResourceNotFoundException;
import com.clinica.odontologia.model.Odontologo;
import com.clinica.odontologia.repository.OdontologoRepository;
import com.clinica.odontologia.service.OdontologoService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OdontologoServiceImpl implements OdontologoService {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoServiceImpl.class);


    @Autowired
    private OdontologoRepository odontologoRepository;

    @Override
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        logger.info("Guardando odontólogo con ID: " + odontologo.getId());
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarTodos() {
        return odontologoRepository.findAll();
    }

    @Override
    public Optional<Odontologo> buscarPorId(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (!odontologo.isPresent()) {
            throw new ResourceNotFoundException("Odontólogo con ID " + id + " no encontrado.");
        }
        return odontologoRepository.findById(id);
    }

    @Override
    public void eliminarOdontologo(Long id) {
        Optional<Odontologo> odontologo = odontologoRepository.findById(id);
        if (!odontologo.isPresent()) {
            throw new ResourceNotFoundException("Odontólogo con ID " + id + " no encontrado.");
        }
        odontologoRepository.deleteById(id);
    }

    @Override
    public List<Odontologo> buscarPorApellido(String apellido) {
        return odontologoRepository.findByApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarPorNombre(String nombre) {
        return odontologoRepository.findByNombre(nombre);
    }

    @Override
    public Optional<Odontologo> buscarPorMatricula(String matricula) {
        return odontologoRepository.findByMatricula(matricula);
    }
}
