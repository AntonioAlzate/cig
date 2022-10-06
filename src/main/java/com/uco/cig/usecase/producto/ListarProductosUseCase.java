package com.uco.cig.usecase.producto;

import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarProductosUseCase {

    private final ProductoRepository productoRepository;

    public ListarProductosUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> listar(){
        return productoRepository.findAll();
    }
}
