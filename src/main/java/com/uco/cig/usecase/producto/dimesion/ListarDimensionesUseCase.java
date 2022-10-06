package com.uco.cig.usecase.producto.dimesion;

import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDimensionesUseCase {

    private final DimensionRepository dimensionRepository;

    public ListarDimensionesUseCase(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    public List<Dimension> listar(){
        return dimensionRepository.findAll();
    }
}
