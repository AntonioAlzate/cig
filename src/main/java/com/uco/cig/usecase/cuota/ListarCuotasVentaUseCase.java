package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCuotasVentaUseCase {

    private final CuotaRepository cuotaRepository;

    public ListarCuotasVentaUseCase(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public List<Cuota> listar(Integer idVenta) {
        return cuotaRepository.findAllByVenta(idVenta);
    }
}
