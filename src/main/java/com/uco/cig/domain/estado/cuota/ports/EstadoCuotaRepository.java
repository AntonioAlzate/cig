package com.uco.cig.domain.estado.cuota.ports;

import com.uco.cig.domain.estado.cuota.EstadoCuota;

import java.util.Optional;

public interface EstadoCuotaRepository {

    Optional<EstadoCuota> findByNombre(String nombre);
}
