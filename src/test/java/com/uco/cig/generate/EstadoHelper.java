package com.uco.cig.generate;

import com.uco.cig.domain.estado.Estado;

import java.util.UUID;

public class EstadoHelper {

    public static Estado crearEstado() {
        return new Estado(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString());
    }
}
