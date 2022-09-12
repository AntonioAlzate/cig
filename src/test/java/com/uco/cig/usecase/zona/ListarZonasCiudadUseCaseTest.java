package com.uco.cig.usecase.zona;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import com.uco.cig.generate.CiudadHelper;
import com.uco.cig.generate.ZonaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarZonasCiudadUseCaseTest {

    ZonaRepository zonaRepository;

    ListarZonasCiudadUseCase listarZonasCiudadUseCase;

    @BeforeEach
    public void setup(){
        zonaRepository = mock(ZonaRepository.class);
        listarZonasCiudadUseCase = new ListarZonasCiudadUseCase(zonaRepository);
    }

    @Test
    public void listarZonasCiudadUseCaseTest() throws BusinessException {
        Ciudad ciudad = CiudadHelper.crearCiudad();
        List<Zona> zonas = Arrays.asList(
                ZonaHelper.crearZona(ciudad),
                ZonaHelper.crearZona(ciudad),
                ZonaHelper.crearZona(ciudad),
                ZonaHelper.crearZona(ciudad)
        );

        when(zonaRepository.findAllByIdCiudad(ciudad.getId())).thenReturn(zonas);

        List<Zona> result = listarZonasCiudadUseCase.listar(ciudad.getId());

        assertEquals(4, result.size());
        assertEquals(zonas, result);
    }
}