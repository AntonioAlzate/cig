package com.uco.cig.domain.liquidacion.ports;

import com.uco.cig.domain.liquidacion.Liquidacion;

import java.util.List;

public interface LiquidacionRepository {

    List<Liquidacion> findAll();
}
