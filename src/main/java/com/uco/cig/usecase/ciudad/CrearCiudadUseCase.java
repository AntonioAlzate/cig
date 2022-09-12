package com.uco.cig.usecase.ciudad;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.ciudad.ports.CiudadRepository;
import com.uco.cig.domain.region.Region;
import org.springframework.stereotype.Service;

@Service
public class CrearCiudadUseCase {

    private final CiudadRepository ciudadRepository;

    public CrearCiudadUseCase(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    public Ciudad crear(String nombreCiudad, Region region) throws BusinessException {
        Ciudad ciudad = Ciudad.nuevo(nombreCiudad, region);
        return ciudadRepository.save(ciudad);
    }
}
