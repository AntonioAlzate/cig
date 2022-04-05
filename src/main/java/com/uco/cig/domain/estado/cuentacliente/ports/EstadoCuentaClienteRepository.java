package com.uco.cig.domain.estado.cuentacliente.ports;

import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;

public interface EstadoCuentaClienteRepository {

    EstadoCuentaCliente findByEstado(String nombre);
}
