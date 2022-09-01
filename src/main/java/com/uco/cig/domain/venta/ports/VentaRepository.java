package com.uco.cig.domain.venta.ports;

import com.uco.cig.domain.venta.Venta;

import java.time.OffsetDateTime;
import java.util.List;

public interface VentaRepository {

    List<Venta> findAll();
    Venta save(Venta venta);
    List<Venta> findAllByIdTrabajadorAndFechaRealizacion(Integer idTrabajador, OffsetDateTime fechaRealizacion);
}
