package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.estado.ventas.ports.EstadoVentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObtenerEstadoVentaCanceladaUseCaseTest {

    private static final String ESTADO_VENTA_NO_ENCONTRADO = "El estado de venta no ha sido encontrado";

    private static final String ESTADO_VENTA_CANCELADA = "CANCELADA";

    EstadoVentaRepository estadoVentaRepository;

    ObtenerEstadoVentaCanceladaUseCase obtenerEstadoVentaCanceladaUseCase;

    @BeforeEach
    void setup() {
        estadoVentaRepository = mock(EstadoVentaRepository.class);
        obtenerEstadoVentaCanceladaUseCase = new ObtenerEstadoVentaCanceladaUseCase(estadoVentaRepository);
    }

    @Test
    void obtenerEstadoVentaActivaUseCaseTest() {
        Optional<EstadoVenta> estadoVenta = Optional.of(new EstadoVenta(1, ESTADO_VENTA_CANCELADA));

        when(estadoVentaRepository.findByNombre(ESTADO_VENTA_CANCELADA)).thenReturn(estadoVenta);

        EstadoVenta result = obtenerEstadoVentaCanceladaUseCase.obtener();

        assertEquals(estadoVenta.get(), result);
    }

    @Test
    void estadoVentaNoEncontradoTest() {

        when(estadoVentaRepository.findByNombre(ESTADO_VENTA_CANCELADA)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> obtenerEstadoVentaCanceladaUseCase.obtener(),
                "se esperaba excepcion"
        );

        assertTrue(exception.getMessage().contains(ESTADO_VENTA_NO_ENCONTRADO));
    }
}