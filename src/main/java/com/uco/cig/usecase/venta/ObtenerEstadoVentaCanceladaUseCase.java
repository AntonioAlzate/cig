package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.estado.ventas.ports.EstadoVentaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObtenerEstadoVentaCanceladaUseCase {

    private static final String ESTADO_VENTA_NO_ENCONTRADO = "El estado de venta no ha sido encontrado";

    private static final String ESTADO_VENTA_CANCELADA = "CANCELADA";

    private final EstadoVentaRepository estadoVentaRepository;

    public ObtenerEstadoVentaCanceladaUseCase(EstadoVentaRepository estadoVentaRepository) {
        this.estadoVentaRepository = estadoVentaRepository;
    }


    public EstadoVenta obtener(){
        Optional<EstadoVenta> estadoVenta = estadoVentaRepository.findByNombre(ESTADO_VENTA_CANCELADA);

        if(estadoVenta.isEmpty())
            throw new NotFoundException(ESTADO_VENTA_NO_ENCONTRADO);

        return estadoVenta.get();
    }
}
