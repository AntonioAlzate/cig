package com.uco.cig.usecase.producto;

import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarProductosConPaginacionUseCase {

    private final ProductoRepository productoRepository;

    public ConsultarProductosConPaginacionUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> consultar(int page, int size, String order, boolean asc){
        return productoRepository.findAll(page,size,order,asc);
    }
}
