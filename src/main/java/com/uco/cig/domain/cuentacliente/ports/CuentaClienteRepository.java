package com.uco.cig.domain.cuentacliente.ports;

import com.uco.cig.domain.cuentacliente.CuentaCliente;

public interface CuentaClienteRepository {

    CuentaCliente save(CuentaCliente cuentaCliente);
}
