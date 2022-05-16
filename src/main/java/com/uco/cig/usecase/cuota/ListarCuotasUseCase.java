package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ListarCuotasUseCase {

    private final CuotaRepository cuotaRepository;

    public ListarCuotasUseCase(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public List<Cuota> listar(){
        return cuotaRepository.findAll();
    }
}
