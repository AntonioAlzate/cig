package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.generate.VentaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarVentasUseCaseTest {

    VentaRepository ventaRepository;

    ListarVentasUseCase listarVentasUseCase;

    @BeforeEach
    public void setup(){
        ventaRepository = mock(VentaRepository.class);
        listarVentasUseCase = new ListarVentasUseCase(ventaRepository);
    }

    @Test
    void listarVentasUseCaseTest() throws BusinessException {
        List<Venta> ventas = Arrays.asList(
                VentaHelper.crearVenta(),
                VentaHelper.crearVenta(),
                VentaHelper.crearVenta(),
                VentaHelper.crearVenta(),
                VentaHelper.crearVenta()
        );

        when(ventaRepository.findAll()).thenReturn(ventas);

        List<Venta> result = listarVentasUseCase.listar();

        assertEquals(5, result.size());
        assertEquals(ventas, result);
        Mockito.verify(ventaRepository).findAll();
    }

}