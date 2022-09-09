package com.uco.cig.usecase.ciudad;

import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.ciudad.ports.CiudadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCiudadesUseCase {

    private final CiudadRepository ciudadRepository;

    public ListarCiudadesUseCase(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public List<Ciudad> listar(Integer idRegion){
        return ciudadRepository.findAll(idRegion);
    }
}
