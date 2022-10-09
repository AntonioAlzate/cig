package com.uco.cig.domain.estado.liquidacion.ports;

import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;

import java.util.Optional;

public interface EstadoLiquidacionRepository {

    Optional<EstadoLiquidacion> findByNombre(String nombre);
}
