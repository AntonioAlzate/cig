package com.uco.cig.generate;

import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;

import java.util.UUID;

public class EstadoLiquidacionHelper {
    public static EstadoLiquidacion crearEstadoLiquidacion(){
        return new EstadoLiquidacion(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
