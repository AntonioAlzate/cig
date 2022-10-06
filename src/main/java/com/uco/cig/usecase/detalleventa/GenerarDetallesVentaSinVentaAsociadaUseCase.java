package com.uco.cig.usecase.detalleventa;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.precio.Precio;
import com.uco.cig.domain.producto.Producto;
import com.uco.cig.domain.producto.ports.ProductoRepository;
import com.uco.cig.shared.dtos.DetalleVentaDTO;
import com.uco.cig.usecase.precio.ObtenerPrecioActualDeProductoUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerarDetallesVentaSinVentaAsociadaUseCase {

    private static final String PRODUCTO_NO_ENCONTRADO = "Valide la existencia de los productos agregados en la venta en el sistema";

    private final ProductoRepository productoRepository;

    private final ObtenerPrecioActualDeProductoUseCase precioActualDeProductoUseCase;

    public GenerarDetallesVentaSinVentaAsociadaUseCase(ProductoRepository productoRepository, ObtenerPrecioActualDeProductoUseCase precioActualDeProductoUseCase) {
        this.productoRepository = productoRepository;
        this.precioActualDeProductoUseCase = precioActualDeProductoUseCase;
    }

    public List<DetalleVenta> generar(List<DetalleVentaDTO> detallesVenta, Integer idModalidad) throws BusinessException {
        List<DetalleVenta> detalles = new ArrayList<>();

        for (DetalleVentaDTO detalleVenta : detallesVenta) {
            Producto producto = productoRepository.findById(detalleVenta.getIdProducto()).orElseThrow(() -> new NotFoundException(PRODUCTO_NO_ENCONTRADO));

            Precio precio = precioActualDeProductoUseCase.obtener(producto.getId(), idModalidad);

            BigDecimal valorSinDescuento = precio.getValor().multiply(BigDecimal.valueOf(detalleVenta.getCantidad()));
            BigDecimal valorFinal = valorSinDescuento.subtract(detalleVenta.getDescuentoAdicional());

            DetalleVenta detalleInsertar = DetalleVenta.nuevo(
                    detalleVenta.getCantidad(),
                    valorFinal,
                    detalleVenta.getDescuentoAdicional(),
                    detalleVenta.getJustificacion(),
                    null,
                    producto
            );

            detalles.add(detalleInsertar);
        }

        return detalles;
    }
}
