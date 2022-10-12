package com.uco.cig.domain.producto.ports;

import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.producto.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoRepository {

    Producto save(Producto producto, Color color);
    List<Producto> findAll();
    List<Producto> findAll(int page, int size, String order, boolean asc);
    Optional<Producto> findByReferencia(String referencia);
    Optional<Producto> findById(Integer id);

    Producto cambiarEstado(Integer idProducto, String estadoNuevo);

    Producto update(Producto producto, Integer idColor);
}
