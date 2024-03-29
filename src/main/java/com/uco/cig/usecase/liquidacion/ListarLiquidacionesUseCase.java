package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarLiquidacionesUseCase {

    private final LiquidacionRepository liquidacionRepository;

    public ListarLiquidacionesUseCase(LiquidacionRepository liquidacionRepository) {
        this.liquidacionRepository = liquidacionRepository;
    }

    public List<Liquidacion> listar(){
        return liquidacionRepository.findAll();
    }
}
