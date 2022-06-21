package com.uco.cig.domain.cliente.ports;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.persona.Persona;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository {

    Cliente save(Cliente cliente);
    Optional<Cliente> findById(Integer id);
    Optional<Cliente> findByIdPersona(Persona idPersona);
    Boolean existsByIdPersona(Persona idPersona);
    List<Cliente> findAll();
    Cliente findByIdentificacion(String identificacion);
    Cliente cambiarEstado(Integer idCliente, String estado);
    Cliente cambiarEstadoCuenta(Integer idCliente, String estadoNuevo);

    List<Cliente> findClientesConIdZona(Integer idZona);
}
