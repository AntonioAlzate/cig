package com.uco.cig.generate;

import com.uco.cig.domain.estado.cuota.EstadoCuota;

import java.util.UUID;

public class EstadoCuotaHelper {
    public static EstadoCuota crearEstadoCuota(){
        return new EstadoCuota(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
    public static EstadoCuota EstadoCuotaCancelada(){
        return new EstadoCuota(
                2,
                "CANCELADA"
        );
    }
}
