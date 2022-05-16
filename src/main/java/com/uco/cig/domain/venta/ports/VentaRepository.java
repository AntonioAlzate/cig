package com.uco.cig.domain.venta.ports;

import com.uco.cig.domain.venta.Venta;

import java.util.List;

public interface VentaRepository {

    List<Venta> findAll();
}
