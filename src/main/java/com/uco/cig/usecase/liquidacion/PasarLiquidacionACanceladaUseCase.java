package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import org.springframework.stereotype.Service;

@Service
public class PasarLiquidacionACanceladaUseCase {

    private final LiquidacionRepository liquidacionRepository;

    public PasarLiquidacionACanceladaUseCase(LiquidacionRepository liquidacionRepository) {
        this.liquidacionRepository = liquidacionRepository;
    }

    public Liquidacion cancelar() {
        // todo:
        return null;
    }
}
