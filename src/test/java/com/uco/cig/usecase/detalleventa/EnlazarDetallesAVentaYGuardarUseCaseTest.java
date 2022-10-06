package com.uco.cig.usecase.detalleventa;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.detalle.venta.ports.DetalleVentaRepository;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.generate.GeneralHelper;
import com.uco.cig.generate.ProductoHelper;
import com.uco.cig.generate.VentaHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

class EnlazarDetallesAVentaYGuardarUseCaseTest {

    DetalleVentaRepository detalleVentaRepository;

    EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase;

    @BeforeEach
    public void setup(){
        detalleVentaRepository = mock(DetalleVentaRepository.class);
        enlazarDetallesAVentaYGuardarUseCase = new EnlazarDetallesAVentaYGuardarUseCase(detalleVentaRepository);
    }

    @Test
    void enlazarDetallesAVentaYGuardarTest() throws BusinessException {
        Venta venta = VentaHelper.crearVenta();
        List<DetalleVenta> detalleVentas = Arrays.asList(
                DetalleVenta.nuevo(
                        GeneralHelper.obtenerEnteroAleatorio(),
                        GeneralHelper.obtenerValorBigDecimalAleatorio(),
                        GeneralHelper.obtenerValorBigDecimalAleatorio(),
                        UUID.randomUUID().toString(),
                        null,
                        ProductoHelper.crearProducto()
                        ),
                DetalleVenta.nuevo(
                        GeneralHelper.obtenerEnteroAleatorio(),
                        GeneralHelper.obtenerValorBigDecimalAleatorio(),
                        GeneralHelper.obtenerValorBigDecimalAleatorio(),
                        UUID.randomUUID().toString(),
                        null,
                        ProductoHelper.crearProducto()
                        )
        );

        when(detalleVentaRepository.saveAll(anyList())).thenReturn(detalleVentas);

        enlazarDetallesAVentaYGuardarUseCase.enlazarGuardar(detalleVentas, venta);

        Mockito.verify(detalleVentaRepository).saveAll(anyList());
    }

}