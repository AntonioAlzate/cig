package com.uco.cig.usecase.producto.dimesion;

import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDimensionesCategoriaUseCase {

    private final DimensionRepository dimensionRepository;

    public ListarDimensionesCategoriaUseCase(DimensionRepository dimensionRepository) {
        this.dimensionRepository = dimensionRepository;
    }

    public List<Dimension> listar(Integer idCategoria){
        return dimensionRepository.findAllByCategoria(idCategoria);
    }
}
