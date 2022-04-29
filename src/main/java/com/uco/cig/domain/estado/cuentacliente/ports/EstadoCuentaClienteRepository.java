package com.uco.cig.domain.estado.cuentacliente.ports;

import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;

import java.util.Optional;

public interface EstadoCuentaClienteRepository {

    Optional<EstadoCuentaCliente> findByEstado(String nombre);
}
