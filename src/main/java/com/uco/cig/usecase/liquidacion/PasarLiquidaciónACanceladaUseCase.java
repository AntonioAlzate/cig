package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class PasarLiquidaciónACanceladaUseCase {

    private final LiquidacionRepository liquidacionRepository;

    public PasarLiquidaciónACanceladaUseCase(LiquidacionRepository liquidacionRepository) {
        this.liquidacionRepository = liquidacionRepository;
    }

    public Liquidacion cancelar() {
        // todo:
        return null;
    }
}
