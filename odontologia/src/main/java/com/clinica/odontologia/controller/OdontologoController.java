package com.clinica.odontologia.controller;

import com.clinica.odontologia.model.Odontologo;
import com.clinica.odontologia.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {

    private static final Logger logger = LoggerFactory.getLogger(OdontologoController.class);

    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo) {
        Odontologo nuevoOdontologo = odontologoService.guardarOdontologo(odontologo);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoOdontologo);
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos() {
        List<Odontologo> odontologos = odontologoService.listarTodos();
        return ResponseEntity.ok(odontologos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> obtenerOdontologoPorId(@PathVariable Long id) {
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        return odontologo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        logger.info("Solicitud para actualizar odontólogo con ID: " + id);

        Optional<Odontologo> odontologoExistente = odontologoService.buscarPorId(id);
        if (odontologoExistente.isPresent()) {
            logger.info("Odontólogo encontrado, actualizando datos.");
            odontologo.setId(id);
            logger.info("Asignando ID: " + id + " al odontólogo.");
            Odontologo odontologoActualizado = odontologoService.guardarOdontologo(odontologo);
            logger.info("Odontólogo actualizado: " + odontologoActualizado.toString());
            return ResponseEntity.ok(odontologoActualizado);
        } else {
            logger.warn("Odontólogo con ID " + id + " no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Long id) {
        Optional<Odontologo> odontologo = odontologoService.buscarPorId(id);
        if (odontologo.isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarPorApellido(@PathVariable String apellido) {
        List<Odontologo> odontologos = odontologoService.buscarPorApellido(apellido);
        return odontologos.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(odontologos);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarPorNombre(@PathVariable String nombre) {
        List<Odontologo> odontologos = odontologoService.buscarPorNombre(nombre);
        return odontologos.isEmpty() ? ResponseEntity.status(HttpStatus.NOT_FOUND).build() : ResponseEntity.ok(odontologos);
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Odontologo> buscarPorMatricula(@PathVariable String matricula) {
        Optional<Odontologo> odontologo = odontologoService.buscarPorMatricula(matricula);
        return odontologo.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
