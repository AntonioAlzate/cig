package com.uco.cig.usecase.zona;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import com.uco.cig.generate.ZonaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarZonasUseCaseTest {

    ZonaRepository zonaRepository;

    ListarZonasUseCase listarZonasUseCase;

    @BeforeEach
    public void setup(){
        zonaRepository = mock(ZonaRepository.class);
        listarZonasUseCase = new ListarZonasUseCase(zonaRepository);
    }

    @Test
    public void listarZonasUseCaseTest() throws BusinessException {
        List<Zona> zonas = Arrays.asList(
                ZonaHelper.crearZona(),
                ZonaHelper.crearZona(),
                ZonaHelper.crearZona()
        );

        when(zonaRepository.findAll()).thenReturn(zonas);

        List<Zona> result = listarZonasUseCase.listar();

        assertEquals(3, result.size());
        assertEquals(zonas, result);
    }

}