package com.uco.cig.domain.detalle.venta.ports;

import com.uco.cig.domain.detalle.venta.DetalleVenta;

import java.util.List;

public interface DetalleVentaRepository {

    List<DetalleVenta> saveAll(List<DetalleVenta> detalles);
}
