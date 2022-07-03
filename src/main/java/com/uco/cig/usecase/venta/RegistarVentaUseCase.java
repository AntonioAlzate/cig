package com.uco.cig.usecase.venta;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;

public class RegistarVentaUseCase {

    private final VentaRepository ventaRepository;

    public RegistarVentaUseCase(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public Venta realizarVenta(){
        return null;
        //todo:
    }
}
