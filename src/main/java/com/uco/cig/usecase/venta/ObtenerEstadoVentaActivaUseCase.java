package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.estado.ventas.ports.EstadoVentaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ObtenerEstadoVentaActivaUseCase {

    private static final String ESTADO_VENTA_NO_ENCONTRADO = "El estado de venta no ha sido encontrado";

    private static final String ESTADO_VENTA_ACITVA = "ACTIVA";

    private final EstadoVentaRepository estadoVentaRepository;

    public ObtenerEstadoVentaActivaUseCase(EstadoVentaRepository estadoVentaRepository) {
        this.estadoVentaRepository = estadoVentaRepository;
    }

    public EstadoVenta obtener(){
        Optional<EstadoVenta> estadoVenta = estadoVentaRepository.findByNombre(ESTADO_VENTA_ACITVA);

        if(estadoVenta.isEmpty())
            throw new NotFoundException(ESTADO_VENTA_NO_ENCONTRADO);

        return estadoVenta.get();
    }
}
