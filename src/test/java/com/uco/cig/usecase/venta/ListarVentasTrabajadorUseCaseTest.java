package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.generate.TrabajadorHelper;
import com.uco.cig.generate.VentaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarVentasTrabajadorUseCaseTest {

    VentaRepository ventaRepository;

    ListarVentasTrabajadorUseCase listarVentasTrabajadorUseCase;

    @BeforeEach
    public void setup(){
        ventaRepository = mock(VentaRepository.class);
        listarVentasTrabajadorUseCase = new ListarVentasTrabajadorUseCase(ventaRepository);
    }

    @Test
    public void listarVentasTrabajadorUseCaseTest() throws BusinessException {
        Trabajador trabajador = TrabajadorHelper.crearTrabajador();
        List<Venta> ventas = Arrays.asList(
                VentaHelper.crearVenta(trabajador),
                VentaHelper.crearVenta(trabajador),
                VentaHelper.crearVenta(trabajador)
        );

        when(ventaRepository.findAllByIdTrabajadorAndFechaRealizacion(trabajador.getId(), ventas.get(0).getFecha())).thenReturn(ventas);

        List<Venta> result = listarVentasTrabajadorUseCase.obtener(trabajador.getId(), ventas.get(0).getFecha());

        assertEquals(3, result.size());
        assertEquals(ventas, result);
    }

}