package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import com.uco.cig.generate.TrabajadorHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarTrabajadoresUseCaseTest {

    TrabajadorRepository trabajadorRepository;

    ListarTrabajadoresUseCase listarTrabajadoresUseCase;

    @BeforeEach
    public void setup(){
        trabajadorRepository = mock(TrabajadorRepository.class);
        listarTrabajadoresUseCase = new ListarTrabajadoresUseCase(trabajadorRepository);
    }

    @Test
    public void listarTrabajadoresUseCaseTest() throws BusinessException {
        List<Trabajador> trabajadores = Arrays.asList(
                TrabajadorHelper.crearTrabajador(),
                TrabajadorHelper.crearTrabajador(),
                TrabajadorHelper.crearTrabajador(),
                TrabajadorHelper.crearTrabajador()
        );

        when(trabajadorRepository.findAll()).thenReturn(trabajadores);

        List<Trabajador> result = listarTrabajadoresUseCase.listar();

        assertEquals(4, result.size());
        assertEquals(trabajadores, result);
    }
}