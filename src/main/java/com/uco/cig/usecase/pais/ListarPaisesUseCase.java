package com.uco.cig.usecase.pais;

import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.pais.ports.PaisRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarPaisesUseCase {

    private final PaisRepository paisRepository;

    public ListarPaisesUseCase(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }

    public List<Pais> listar() {
        return paisRepository.findAll();
    }
}
