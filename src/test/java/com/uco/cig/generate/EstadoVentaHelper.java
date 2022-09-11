package com.uco.cig.generate;

import com.uco.cig.domain.estado.ventas.EstadoVenta;

import java.util.UUID;

public class EstadoVentaHelper {
    public static EstadoVenta crearEstadoVenta(){
        return new EstadoVenta(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
