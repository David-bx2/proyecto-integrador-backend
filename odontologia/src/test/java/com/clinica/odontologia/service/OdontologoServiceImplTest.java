package com.clinica.odontologia.service;

import com.clinica.odontologia.model.Odontologo;
import com.clinica.odontologia.repository.OdontologoRepository;
import com.clinica.odontologia.service.impl.OdontologoServiceImpl;
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
public class OdontologoServiceImplTest {

    @Mock
    private OdontologoRepository odontologoRepository;

    @InjectMocks
    private OdontologoServiceImpl odontologoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarOdontologo() {
        Odontologo odontologo = new Odontologo();
        odontologo.setNombre("Juan");
        odontologo.setApellido("Perez");
        odontologo.setMatricula("12345");

        Mockito.when(odontologoRepository.save(Mockito.any(Odontologo.class))).thenReturn(odontologo);

        Odontologo nuevoOdontologo = odontologoService.guardarOdontologo(odontologo);

        Assertions.assertNotNull(nuevoOdontologo);
        Assertions.assertEquals("Juan", nuevoOdontologo.getNombre());
    }

    @Test
    public void testListarTodos() {
        List<Odontologo> odontologos = Arrays.asList(new Odontologo(), new Odontologo());
        Mockito.when(odontologoRepository.findAll()).thenReturn(odontologos);

        List<Odontologo> resultado = odontologoService.listarTodos();

        Assertions.assertEquals(2, resultado.size());
    }

    @Test
    public void testBuscarPorId() {
        Odontologo odontologo = new Odontologo();
        odontologo.setId(1L);
        odontologo.setNombre("Juan");

        Mockito.when(odontologoRepository.findById(1L)).thenReturn(Optional.of(odontologo));

        Optional<Odontologo> resultado = odontologoService.buscarPorId(1L);

        Assertions.assertTrue(resultado.isPresent());
        Assertions.assertEquals("Juan", resultado.get().getNombre());
    }

    @Test
    public void testEliminarOdontologo() {
        odontologoService.eliminarOdontologo(1L);
        Mockito.verify(odontologoRepository, Mockito.times(1)).deleteById(1L);
    }
}
