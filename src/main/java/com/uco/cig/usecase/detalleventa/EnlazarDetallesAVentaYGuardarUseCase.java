package com.uco.cig.usecase.detalleventa;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.detalle.venta.ports.DetalleVentaRepository;
import com.uco.cig.domain.venta.Venta;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnlazarDetallesAVentaYGuardarUseCase {

    private final DetalleVentaRepository detalleVentaRepository;

    public EnlazarDetallesAVentaYGuardarUseCase(DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
    }

    public void enlazarGuardar(List<DetalleVenta> detallesVenta, Venta venta) {
        detallesVenta = detallesVenta.stream().map(detalleVenta -> {
            try {
                detalleVenta.setVenta(venta);
            } catch (BusinessException e) {
                e.printStackTrace();
            }
            return detalleVenta;
        }).collect(Collectors.toList());

        detalleVentaRepository.saveAll(detallesVenta);
    }
}
