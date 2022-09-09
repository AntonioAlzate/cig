package com.uco.cig.usecase.zona;

import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarZonasUseCase {

    private final ZonaRepository zonaRepository;

    public ListarZonasUseCase(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public List<Zona> listar() {
        return zonaRepository.findAll();
    }
}
