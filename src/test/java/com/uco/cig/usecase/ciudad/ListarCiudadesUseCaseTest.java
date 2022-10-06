package com.uco.cig.usecase.ciudad;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.ciudad.ports.CiudadRepository;
import com.uco.cig.domain.region.Region;
import com.uco.cig.generate.CiudadHelper;
import com.uco.cig.generate.RegionHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarCiudadesUseCaseTest {

    CiudadRepository ciudadRepository;

    ListarCiudadesUseCase listarCiudadesUseCase;

    @BeforeEach
    public void setup(){
        ciudadRepository = mock(CiudadRepository.class);
        listarCiudadesUseCase = new ListarCiudadesUseCase(ciudadRepository);
    }

    @Test
    void listarCiudadesUseCaseTest() throws BusinessException {
        Region region = RegionHelper.crearRegion();
        List<Ciudad> ciudades = Arrays.asList(
                CiudadHelper.crearCiudad(region),
                CiudadHelper.crearCiudad(region),
                CiudadHelper.crearCiudad(region)
        );

        when(ciudadRepository.findAll(region.getId())).thenReturn(ciudades);

        List<Ciudad> result = listarCiudadesUseCase.listar(region.getId());

        assertEquals(3, result.size());
        assertEquals(ciudades, result);
    }

}