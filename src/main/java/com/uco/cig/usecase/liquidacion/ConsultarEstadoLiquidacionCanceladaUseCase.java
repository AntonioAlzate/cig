package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.estado.liquidacion.ports.EstadoLiquidacionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultarEstadoLiquidacionCanceladaUseCase {

    private static final String ESTADO_LIQUIDACION_CANCELADA = "CANCELADA";
    private static final String ESTADO_LIQUIDACION_CANCELADA_NO_EXISTE = "El estado de liquidación cancelada no ha sido encontrada en el sistema.";


    private final EstadoLiquidacionRepository estadoLiquidacionRepository;

    public ConsultarEstadoLiquidacionCanceladaUseCase(EstadoLiquidacionRepository estadoLiquidacionRepository) {
        this.estadoLiquidacionRepository = estadoLiquidacionRepository;
    }

    public EstadoLiquidacion consultar() {
        Optional<EstadoLiquidacion> estadoLiquidacion = estadoLiquidacionRepository.findByNombre(ESTADO_LIQUIDACION_CANCELADA);

        if(estadoLiquidacion.isEmpty())
            throw new NotFoundException(ESTADO_LIQUIDACION_CANCELADA_NO_EXISTE);

        return estadoLiquidacion.get();
    }
}
