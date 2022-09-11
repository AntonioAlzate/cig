package com.uco.cig.usecase.zona;

import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarZonasCiudadUseCase {

    private final ZonaRepository zonaRepository;

    public ListarZonasCiudadUseCase(ZonaRepository zonaRepository) {
        this.zonaRepository = zonaRepository;
    }

    public List<Zona> listar(Integer idCiudad) {
        return zonaRepository.findAllByIdCiudad(idCiudad);
    }
}
