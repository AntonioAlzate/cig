package com.uco.cig.domain.barrio.ports;

import com.uco.cig.domain.barrio.Barrio;

import java.util.List;
import java.util.Optional;

public interface BarrioRepository {
    Optional<Barrio> findById(Integer id);
    List<Barrio> findAll();

    List<Barrio> findAllByIdZAndIdZonaIdCiudad(Integer idCiudad);
}
