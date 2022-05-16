package com.uco.cig.usecase.venta;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListarVentasUseCase {

    private final VentaRepository ventaRepository;

    public ListarVentasUseCase(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    public List<Venta> listar(){
        return ventaRepository.findAll();
    }
}
