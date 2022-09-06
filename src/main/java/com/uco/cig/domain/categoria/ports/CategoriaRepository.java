package com.uco.cig.domain.categoria.ports;

import com.uco.cig.domain.categoria.Categoria;

import java.util.List;
import java.util.Optional;

public interface CategoriaRepository {

    Optional<Categoria> findById(Integer id);
    Categoria save(Categoria categoria);

    List<Categoria> findAll();

    Categoria findByNombre(String nombreCategoria);
}
