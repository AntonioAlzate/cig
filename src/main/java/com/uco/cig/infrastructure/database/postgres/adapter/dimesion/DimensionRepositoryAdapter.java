package com.uco.cig.infrastructure.database.postgres.adapter.dimesion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.categoria.Categoria;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CategoriaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.DimensionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CategoriaEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.DimensionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DimensionRepositoryAdapter implements DimensionRepository {

    private static final String CATEGORIA_NO_EXISTENTE = "La categoria especificada no existe en el sistema";
    private final DimensionEntityRepository dimensionEntityRepository;
    private final CategoriaEntityRepository categoriaEntityRepository;
    private final MapperUtils mapperUtils;

    public DimensionRepositoryAdapter(DimensionEntityRepository dimensionEntityRepository, CategoriaEntityRepository categoriaEntityRepository, MapperUtils mapperUtils) {
        this.dimensionEntityRepository = dimensionEntityRepository;
        this.categoriaEntityRepository = categoriaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Dimension> findById(Integer id) {
        Optional<DimensionEntity> dimensionEntity = dimensionEntityRepository.findById(id);

        if(dimensionEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToDimension().apply(dimensionEntity.get()));
    }

    @Override
    public Dimension save(Dimension dimension) {

        DimensionEntity dimensionEntity = new DimensionEntity(null, dimension.getLargo(), dimension.getAncho(), mapperUtils.mappertoCategoriaEntity().apply(dimension.getCategoria()));

        dimensionEntity = dimensionEntityRepository.save(dimensionEntity);

        return mapperUtils.mapperToDimension().apply(dimensionEntity);
    }

    @Override
    public List<Dimension> findAll() {
        List<DimensionEntity> dimensionEntities = dimensionEntityRepository.findAll();

        if(dimensionEntities.isEmpty())
            return new ArrayList<>();

        return dimensionEntities.stream().map(dimensionEntity -> mapperUtils.mapperToDimension().apply(dimensionEntity)).collect(Collectors.toList());
    }

    @Override
    public Dimension findByAnchoAndLargoAndCategoria(BigDecimal ancho, BigDecimal largo, Categoria categoria) {

        Optional<CategoriaEntity> categoriaEntity = categoriaEntityRepository.findById(categoria.getId());

        if(categoriaEntity.isEmpty())
            throw new NotFoundException(CATEGORIA_NO_EXISTENTE);

        Optional<DimensionEntity> dimensionEntity = dimensionEntityRepository.findByAnchoAndLargoAndIdCategoriaEntity(ancho, largo, categoriaEntity.get());

        if(dimensionEntity.isEmpty())
            return null;

        return mapperUtils.mapperToDimension().apply(dimensionEntity.get());
    }

    @Override
    public List<Dimension> findAllByCategoria(Integer idCategoria) {
        Optional<CategoriaEntity> categoriaEntity = categoriaEntityRepository.findById(idCategoria);

        if(categoriaEntity.isEmpty())
            throw new NotFoundException(CATEGORIA_NO_EXISTENTE);

        List<DimensionEntity> dimensionEntities = dimensionEntityRepository.findAllByIdCategoriaEntity(categoriaEntity.get());

        if(dimensionEntities.isEmpty())
            return new ArrayList<>();

        return dimensionEntities.stream().map(dimensionEntity -> mapperUtils.mapperToDimension().apply(dimensionEntity)).collect(Collectors.toList());
    }
}
