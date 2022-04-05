package com.uco.cig.domain.cliente.ports;

import com.uco.cig.domain.cliente.Cliente;

public interface ClienteRepository {

    Cliente save(Cliente cliente);
}
