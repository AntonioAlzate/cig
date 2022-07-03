package com.uco.cig.domain.producto.ports;

import com.uco.cig.domain.producto.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    Producto save(Producto producto);
    List<Producto> findAll();
    List<Producto> findAll(int page, int size, String order, boolean asc);
    Optional<Producto> findByReferencia(String referencia);
}
