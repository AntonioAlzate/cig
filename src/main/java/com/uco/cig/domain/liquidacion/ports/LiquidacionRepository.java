package com.uco.cig.domain.liquidacion.ports;

import java.util.List;

public interface LiquidacionRepository {

    List<Liquidacion> findAll();
}
