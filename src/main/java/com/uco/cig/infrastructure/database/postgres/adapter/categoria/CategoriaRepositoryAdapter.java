package com.uco.cig.infrastructure.database.postgres.adapter.categoria;

import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.categoria.ports.CategoriaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CategoriaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CategoriaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Categoria save(Categoria categoria) {

        CategoriaEntity categoriaEntity = mapperUtils.mappertoCategoriaEntity().apply(categoria);
        categoriaEntity = categoriaEntityRepository.save(categoriaEntity);
        return mapperUtils.mapperToCategoria().apply(categoriaEntity);
    }

    @Override
    public List<Categoria> findAll() {
        List<CategoriaEntity> categoriaEntities = categoriaEntityRepository.findAll();

        if(categoriaEntities.isEmpty())
            return new ArrayList<>();

        return categoriaEntities.stream().map(categoriaEntity -> mapperUtils.mapperToCategoria().apply(categoriaEntity)).collect(Collectors.toList());
    }
}
