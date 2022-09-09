package com.uco.cig.infrastructure.database.postgres.adapter.ciudad;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.ciudad.Ciudad;
import com.uco.cig.domain.ciudad.ports.CiudadRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.RegionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CiudadEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.RegionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CiudadRepositoryAdapter implements CiudadRepository {

    private static final String REGION_NO_ENCONTRADA = "La región especificada no ha sido encontrada en el sistema";

    private final CiudadEntityRepository ciudadEntityRepository;
    private final RegionEntityRepository regionEntityRepository;
    private final MapperUtils mapperUtils;

    public CiudadRepositoryAdapter(CiudadEntityRepository ciudadEntityRepository, RegionEntityRepository regionEntityRepository, MapperUtils mapperUtils) {
        this.ciudadEntityRepository = ciudadEntityRepository;
        this.regionEntityRepository = regionEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Ciudad> findAll(Integer idRegion) {
        Optional<RegionEntity> regionEntity = regionEntityRepository.findById(idRegion);

        if(regionEntity.isEmpty())
            throw new NotFoundException(REGION_NO_ENCONTRADA);

        List<CiudadEntity> ciudadEntities = ciudadEntityRepository.findAllByIdRegionEntity(regionEntity.get());

        if(ciudadEntities.isEmpty())
            return new ArrayList<>();

        return ciudadEntities.stream().map(ciudadEntity -> mapperUtils.mapperToCiudad().apply(ciudadEntity)).collect(Collectors.toList());
    }
}
