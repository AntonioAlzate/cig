package com.uco.cig.domain.liquidacion.ports;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.trabajador.Trabajador;

import java.util.List;

public interface LiquidacionRepository {

    List<Liquidacion> findAll();

    Liquidacion save(Liquidacion liquidacion);

    List<Liquidacion> findAllByTrabajador(Trabajador trabajador);
}
