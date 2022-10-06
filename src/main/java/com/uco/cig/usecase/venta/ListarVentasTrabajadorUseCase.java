package com.uco.cig.usecase.venta;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ListarVentasTrabajadorUseCase {

    private final VentaRepository ventaRepository;

    public ListarVentasTrabajadorUseCase(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> obtener(Integer idTrabajador, OffsetDateTime fechaRealizacion) {
        return ventaRepository.findAllByIdTrabajadorAndFechaRealizacion(idTrabajador, fechaRealizacion);
    }
}
