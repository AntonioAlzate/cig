package com.uco.cig.usecase.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarBarriosCiudadUseCase {

    private final BarrioRepository barrioRepository;

    public ListarBarriosCiudadUseCase(BarrioRepository barrioRepository) {
        this.barrioRepository = barrioRepository;
    }

    public List<Barrio> listar(Integer idCiudad){
        return barrioRepository.findAllByIdZAndIdZonaIdCiudad(idCiudad);
    }
}
