package com.clinica.odontologia.service;

import com.clinica.odontologia.model.Paciente;
import com.clinica.odontologia.repository.PacienteRepository;
import com.clinica.odontologia.service.impl.PacienteServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PacienteServiceImplTest {

    @Mock
    private PacienteRepository pacienteRepository;

    @InjectMocks
    private PacienteServiceImpl pacienteService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarPaciente() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Ana");
        paciente.setApellido("Gomez");
        paciente.setDni("98765432");

        Mockito.when(pacienteRepository.save(Mockito.any(Paciente.class))).thenReturn(paciente);

        Paciente nuevoPaciente = pacienteService.guardarPaciente(paciente);

        Assertions.assertNotNull(nuevoPaciente);
        Assertions.assertEquals("Ana", nuevoPaciente.getNombre());
    }

    @Test
    public void testListarTodos() {
        List<Paciente> pacientes = Arrays.asList(new Paciente(), new Paciente());
        Mockito.when(pacienteRepository.findAll()).thenReturn(pacientes);

        List<Paciente> resultado = pacienteService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        Paciente paciente = new Paciente();
        paciente.setId(1L);
        paciente.setNombre("Ana");

        Mockito.when(pacienteRepository.findById(1L)).thenReturn(Optional.of(paciente));

        Optional<Paciente> resultado = pacienteService.buscarPorId(1L);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Ana", resultado.get().getNombre());
    }

    @Test
    public void testEliminarPaciente() {
        pacienteService.eliminarPaciente(1L);
        Mockito.verify(pacienteRepository, Mockito.times(1)).deleteById(1L);
    }
}
