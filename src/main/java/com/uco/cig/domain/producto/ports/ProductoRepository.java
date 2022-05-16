package com.uco.cig.domain.producto.ports;

import com.uco.cig.domain.producto.Producto;

import java.util.List;

public interface ProductoRepository {

    Producto save(Producto producto);
    List<Producto> findAll();
}
