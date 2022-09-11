package com.uco.cig.generate;

import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;

import java.util.UUID;

public class EstadoCuentaClienteHelper {
    public static EstadoCuentaCliente crearEstadoCuentaCliente(){
        return new EstadoCuentaCliente(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
                );
    }
}
