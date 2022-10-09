package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.generate.CuotasHelper;
import com.uco.cig.generate.EstadoCuotaHelper;
import com.uco.cig.generate.TrabajadorHelper;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarCuotasCobradasTrabajadorUseCaseTest {

    CuotaRepository cuotaRepository;

    ConsultarEstadoCuotaCanceladaUseCase estadoCuotaCanceladaUseCase;

    ListarCuotasCobradasTrabajadorUseCase listarCuotasCobradasTrabajadorUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        estadoCuotaCanceladaUseCase = mock(ConsultarEstadoCuotaCanceladaUseCase.class);
        listarCuotasCobradasTrabajadorUseCase = new ListarCuotasCobradasTrabajadorUseCase(cuotaRepository, estadoCuotaCanceladaUseCase);
    }

    @Test
    void listarCuotasCobradasTrabajadorUseCaseTest() throws BusinessException {
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