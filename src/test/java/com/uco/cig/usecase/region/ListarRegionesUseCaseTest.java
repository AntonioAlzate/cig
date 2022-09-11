package com.uco.cig.usecase.region;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.region.ports.RegionRepository;
import com.uco.cig.generate.DepartamentosHelper;
import com.uco.cig.generate.RegionHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarRegionesUseCaseTest {

    RegionRepository regionRepository;

    ListarRegionesUseCase listarRegionesUseCase;

    @BeforeEach
    public void setup(){
        regionRepository = mock(RegionRepository.class);
        listarRegionesUseCase = new ListarRegionesUseCase(regionRepository);
    }

    @Test
    public void listarRegionesUseCaseTest() throws BusinessException {
        Departamento departamento = DepartamentosHelper.crearDepartamento();
        List<Region> regiones = Arrays.asList(
                RegionHelper.crearRegion(departamento),
                RegionHelper.crearRegion(departamento)
        );

        when(regionRepository.findAll(departamento.getId())).thenReturn(regiones);

        List<Region> result = listarRegionesUseCase.listar(departamento.getId());

        assertEquals(2, result.size());
        assertEquals(regiones, result);
    }
}