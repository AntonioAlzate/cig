package com.uco.cig.usecase.producto;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CambiarEstadoProductoUseCase {

    private static final String ACTIVO = EstadoEnum.ACTIVO.name();
    private static final String INACTIVO = EstadoEnum.INACTIVO.name();

    private static final String PRODUCTO_NO_ENCONTRADO = "El producto al que desea actualizar el estado no existe.";

    private final ProductoRepository productoRepository;

    public CambiarEstadoProductoUseCase(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public Producto cambiarEstado(Integer idProducto) {
        Optional<Producto> producto = productoRepository.findById(idProducto);

        if(producto.isEmpty())
            throw new NotFoundException(PRODUCTO_NO_ENCONTRADO);

        String estadoActual = producto.get().getEstado().getNombre();
        String estadoNuevo = estadoActual.equals(ACTIVO) ? INACTIVO : ACTIVO;

        return productoRepository.cambiarEstado(idProducto, estadoNuevo);
    }
}
