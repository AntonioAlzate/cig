package com.uco.cig.infrastructure.database.postgres.adapter.categoria;

import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CategoriaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CategoriaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoriaRepositoryAdapter implements CategoriaRepository {

    private final CategoriaEntityRepository categoriaEntityRepository;
    private final MapperUtils mapperUtils;

    public CategoriaRepositoryAdapter(CategoriaEntityRepository categoriaEntityRepository, MapperUtils mapperUtils) {
        this.categoriaEntityRepository = categoriaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Categoria> findById(Integer id) {

        Optional<CategoriaEntity> categoriaEntity = categoriaEntityRepository.findById(id);

        if(categoriaEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToCategoria().apply(categoriaEntity.get()));
    }
}
