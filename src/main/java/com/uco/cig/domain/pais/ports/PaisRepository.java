package com.uco.cig.domain.pais.ports;

import com.uco.cig.domain.pais.Pais;

import java.util.List;

public interface PaisRepository {

    List<Pais> findAll();
}
