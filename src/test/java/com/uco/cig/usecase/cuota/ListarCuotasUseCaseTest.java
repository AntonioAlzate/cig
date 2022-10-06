package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.generate.CuotasHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarCuotasUseCaseTest {

    CuotaRepository cuotaRepository;

    ListarCuotasUseCase listarCuotasUseCase;

    @BeforeEach
    public void setup() {
        cuotaRepository = mock(CuotaRepository.class);
        listarCuotasUseCase = new ListarCuotasUseCase(cuotaRepository);
    }

    @Test
    void listarCuotasUseCaseTest() throws BusinessException {
        List<Cuota> cuotas = Arrays.asList(
                CuotasHelper.crearCuota(),
                CuotasHelper.crearCuota(),
                CuotasHelper.crearCuota(),
                CuotasHelper.crearCuota()
        );

        when(cuotaRepository.findAll()).thenReturn(cuotas);

        List<Cuota> result = listarCuotasUseCase.listar();
        assertEquals(4, result.size());
        assertEquals(cuotas, result);
    }

}