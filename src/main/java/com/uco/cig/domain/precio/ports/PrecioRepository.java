package com.uco.cig.domain.precio.ports;

import com.uco.cig.domain.precio.Precio;

import java.util.List;
import java.util.Optional;

public interface PrecioRepository {

    Precio save(Precio precio);
    Optional<Precio> findByIdProductoAndIdModalidadAndFechaFinIsNull(Integer idProducto, Integer idModalidad);
    List<Precio> findAllByIdProductoEntityAndIdModalidadEntity(Integer idProducto, Integer idModalidad);
}
