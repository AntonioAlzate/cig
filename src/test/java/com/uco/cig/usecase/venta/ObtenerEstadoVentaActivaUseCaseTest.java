package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.estado.ventas.ports.EstadoVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ObtenerEstadoVentaActivaUseCaseTest {

    private static final String ESTADO_VENTA_NO_ENCONTRADO = "El estado de venta no ha sido encontrado";

    private static final String ESTADO_VENTA_ACITVA = "ACTIVA";

    EstadoVentaRepository estadoVentaRepository;

    ObtenerEstadoVentaActivaUseCase obtenerEstadoVentaActivaUseCase;

    @BeforeEach
    void setup() {
        estadoVentaRepository = mock(EstadoVentaRepository.class);
        obtenerEstadoVentaActivaUseCase = new ObtenerEstadoVentaActivaUseCase(estadoVentaRepository);
    }

    @Test
    void obtenerEstadoVentaActivaUseCaseTest() {
        Optional<EstadoVenta> estadoVenta = Optional.of(new EstadoVenta(1, ESTADO_VENTA_ACITVA));

        when(estadoVentaRepository.findByNombre(ESTADO_VENTA_ACITVA)).thenReturn(estadoVenta);

        EstadoVenta result = obtenerEstadoVentaActivaUseCase.obtener();

        assertEquals(estadoVenta.get(), result);
    }

    @Test
    void estadoVentaNoEncontradoTest() {

        when(estadoVentaRepository.findByNombre(ESTADO_VENTA_ACITVA)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> obtenerEstadoVentaActivaUseCase.obtener(),
                "se esperaba excepcion"
        );

        assertTrue(exception.getMessage().contains(ESTADO_VENTA_NO_ENCONTRADO));
    }

}