package com.uco.cig.usecase.venta;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarVentasClienteUseCase {

    private final VentaRepository ventaRepository;

    public ListarVentasClienteUseCase(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listar(Integer idCliente) {
        return ventaRepository.findAllByIdCliente(idCliente);
    }
}
