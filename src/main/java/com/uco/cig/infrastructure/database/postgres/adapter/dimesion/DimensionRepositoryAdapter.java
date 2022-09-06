package com.uco.cig.infrastructure.database.postgres.adapter.dimesion;

import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import com.uco.cig.infrastructure.database.postgres.entities.DimensionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DimensionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DimensionRepositoryAdapter implements DimensionRepository {

    private final DimensionEntityRepository dimensionEntityRepository;
    private final MapperUtils mapperUtils;

    public DimensionRepositoryAdapter(DimensionEntityRepository dimensionEntityRepository, MapperUtils mapperUtils) {
        this.dimensionEntityRepository = dimensionEntityRepository;
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

        DimensionEntity dimensionEntity = new DimensionEntity(null, dimension.getLargo(), dimension.getAncho());

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
}
