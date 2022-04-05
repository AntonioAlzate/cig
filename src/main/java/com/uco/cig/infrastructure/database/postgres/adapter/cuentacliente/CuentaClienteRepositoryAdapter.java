package com.uco.cig.infrastructure.database.postgres.adapter.cuentacliente;

import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.cuentacliente.ports.CuentaClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class CuentaClienteRepositoryAdapter implements CuentaClienteRepository {
    @Override
    public CuentaCliente save(CuentaCliente cuentaCliente) {
        return null;
    }
}
