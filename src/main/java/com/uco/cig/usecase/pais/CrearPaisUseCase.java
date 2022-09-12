package com.uco.cig.usecase.pais;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.pais.ports.PaisRepository;
import org.springframework.stereotype.Service;

@Service
public class CrearPaisUseCase {

    private final PaisRepository paisRepository;

    public CrearPaisUseCase(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public Pais crear(String nombrePais) throws BusinessException {
        Pais pais = Pais.nuevo(nombrePais);
        return paisRepository.save(pais);
    }
}
