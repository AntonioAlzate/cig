package com.uco.cig.domain.estado.ports;

import com.uco.cig.domain.estado.Estado;

import java.util.Optional;

public interface EstadoRepository {

    Optional<Estado> findByNombre(String nombre);
}