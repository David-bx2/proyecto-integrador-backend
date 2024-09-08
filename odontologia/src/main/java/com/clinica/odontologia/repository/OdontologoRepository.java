package com.clinica.odontologia.repository;

import com.clinica.odontologia.model.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OdontologoRepository extends JpaRepository<Odontologo, Long> {
    List<Odontologo> findByApellido(String apellido);
    List<Odontologo> findByNombre(String nombre);
    Optional<Odontologo> findByMatricula(String matricula);
}
