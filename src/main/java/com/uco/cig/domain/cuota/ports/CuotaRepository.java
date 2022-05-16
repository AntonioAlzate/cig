package com.uco.cig.domain.cuota.ports;

import com.uco.cig.domain.cuota.Cuota;

import java.util.List;

public interface CuotaRepository {

    List<Cuota> findAll();
}
