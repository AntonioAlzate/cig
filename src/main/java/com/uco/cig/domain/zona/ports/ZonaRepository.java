package com.uco.cig.domain.zona.ports;

import com.uco.cig.domain.zona.Zona;

import java.util.List;

public interface ZonaRepository {

    List<Zona> findAll();

    List<Zona> findAllByIdCiudad(Integer idCiudad);
}
