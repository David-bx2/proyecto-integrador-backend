package com.clinica.odontologia.repository;

import com.clinica.odontologia.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurnoRepository extends JpaRepository<Turno, Long> {
    List<Turno> findByOdontologo_Id(Long odontologoId);
}
