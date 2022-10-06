package com.uco.cig.usecase.producto.categoria;

import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarCategoriasUseCase {

    private final CategoriaRepository categoriaRepository;

    public ListarCategoriasUseCase(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public List<Categoria> listar(){
        return categoriaRepository.findAll();
    }
}
