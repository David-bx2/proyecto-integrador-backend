package com.clinica.odontologia.service;


import com.clinica.odontologia.model.Turno;
import com.clinica.odontologia.repository.TurnoRepository;
import com.clinica.odontologia.service.impl.TurnoServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class TurnoServiceImplTest {

    @Mock
    private TurnoRepository turnoRepository;

    @InjectMocks
    private TurnoServiceImpl turnoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarTurno() {
        Turno turno = new Turno();
        turno.setFecha(LocalDateTime.now());

        Mockito.when(turnoRepository.save(Mockito.any(Turno.class))).thenReturn(turno);

        Turno nuevoTurno = turnoService.guardarTurno(turno);

        Assertions.assertNotNull(nuevoTurno);
    }

    @Test
    public void testListarTodos() {
        List<Turno> turnos = Arrays.asList(new Turno(), new Turno());
        Mockito.when(turnoRepository.findAll()).thenReturn(turnos);

        List<Turno> resultado = turnoService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        Turno turno = new Turno();
        turno.setId(1L);
        turno.setFecha(LocalDateTime.now());

        Mockito.when(turnoRepository.findById(1L)).thenReturn(Optional.of(turno));

        Optional<Turno> resultado = turnoService.buscarPorId(1L);

        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void testEliminarTurno() {
        turnoService.eliminarTurno(1L);
        Mockito.verify(turnoRepository, Mockito.times(1)).deleteById(1L);
    }
}
