package com.uco.cig.domain.estado.ventas.ports;

import com.uco.cig.domain.estado.ventas.EstadoVenta;

import java.util.Optional;

public interface EstadoVentaRepository {

    Optional<EstadoVenta> findByNombre(String nombre);
}
