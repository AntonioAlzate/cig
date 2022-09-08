package com.uco.cig.usecase.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListarBarriosUseCase {

    private final BarrioRepository barrioRepository;

    public ListarBarriosUseCase(BarrioRepository barrioRepository) {
        this.barrioRepository = barrioRepository;
    }

    public List<Barrio> listar(){
        return barrioRepository.findAll();
    }
}
