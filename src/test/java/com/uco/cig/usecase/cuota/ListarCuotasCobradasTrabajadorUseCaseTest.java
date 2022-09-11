package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.generate.CuotasHelper;
import com.uco.cig.generate.EstadoCuotaHelper;
import com.uco.cig.generate.TrabajadorHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarCuotasCobradasTrabajadorUseCaseTest {

    CuotaRepository cuotaRepository;

    ListarCuotasCobradasTrabajadorUseCase listarCuotasCobradasTrabajadorUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        listarCuotasCobradasTrabajadorUseCase = new ListarCuotasCobradasTrabajadorUseCase(cuotaRepository);
    }

    @Test
    public void listarCuotasCobradasTrabajadorUseCaseTest() throws BusinessException {
        Trabajador trabajador = TrabajadorHelper.crearTrabajador();
        Cuota cuotaTest = CuotasHelper.crearCuota(trabajador, EstadoCuotaHelper.EstadoCuotaCancelada());
        List<Cuota> cuotas = Arrays.asList(
                cuotaTest
        );

        when(cuotaRepository.findAllByIdTrabajadorAndIdEstadoCuotaAndFechaRealizacion(
                trabajador.getId(),
                cuotaTest.getEstadoCuota().getId(),
                cuotaTest.getFechaRealizacion()
        )).thenReturn(cuotas);

        List<Cuota> result = listarCuotasCobradasTrabajadorUseCase.obtener(
                trabajador.getId(),
                cuotaTest.getFechaRealizacion()
        );

        assertEquals(1, result.size());
        assertEquals(cuotas, result);
    }

}