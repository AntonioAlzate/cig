package com.uco.cig.domain.ciudad.ports;

import com.uco.cig.domain.ciudad.Ciudad;

import java.util.List;

public interface CiudadRepository {

    List<Ciudad> findAll(Integer idRegion);
}
