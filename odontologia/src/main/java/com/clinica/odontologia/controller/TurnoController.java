package com.clinica.odontologia.controller;


import com.clinica.odontologia.model.Turno;
import com.clinica.odontologia.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        Turno nuevoTurno = turnoService.guardarTurno(turno);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTurno);
    }

    @GetMapping
    public ResponseEntity<List<Turno>> listarTurnos() {
        List<Turno> turnos = turnoService.listarTodos();
        return ResponseEntity.ok(turnos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> obtenerTurnoPorId(@PathVariable Long id) {
        Optional<Turno> turno = turnoService.buscarPorId(id);
        return turno.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable Long id, @RequestBody Turno turno) {
        Optional<Turno> turnoExistente = turnoService.buscarPorId(id);
        if (turnoExistente.isPresent()) {
            turno.setId(id);
            turnoService.guardarTurno(turno);
            return ResponseEntity.ok(turno);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        Optional<Turno> turno = turnoService.buscarPorId(id);
        if (turno.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
