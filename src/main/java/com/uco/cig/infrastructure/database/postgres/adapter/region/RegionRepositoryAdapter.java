package com.uco.cig.infrastructure.database.postgres.adapter.region;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.region.Region;
import com.uco.cig.domain.region.ports.RegionRepository;
import com.uco.cig.infrastructure.database.postgres.entities.DepartamentoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.RegionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DepartamentoEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.RegionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RegionRepositoryAdapter implements RegionRepository {

    private static final String DEPARTAMENTO_NO_ENCONTRADO = "El departamento ingresado no ha sido encontrado en el sistema";
    private static final String REGION_YA_REGISTRADA = "Ya existe una región con el mismo nombre registrada en el sistema";

    private final RegionEntityRepository regionEntityRepository;
    private final DepartamentoEntityRepository departamentoEntityRepository;
    private final MapperUtils mapperUtils;

    public RegionRepositoryAdapter(RegionEntityRepository regionEntityRepository, DepartamentoEntityRepository departamentoEntityRepository, MapperUtils mapperUtils) {
        this.regionEntityRepository = regionEntityRepository;
        this.departamentoEntityRepository = departamentoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Region> findAll(Integer idDepartamento) {

        Optional<DepartamentoEntity> departamentoEntity = departamentoEntityRepository.findById(idDepartamento);

        if(departamentoEntity.isEmpty())
            throw new NotFoundException(DEPARTAMENTO_NO_ENCONTRADO);

        List<RegionEntity> regionEntities = regionEntityRepository.findAllByIdDepartamento(departamentoEntity.get());

        if(regionEntities.isEmpty())
            return new ArrayList<>();

        return regionEntities.stream().map(regionEntity -> mapperUtils.mapperToRegion().apply(regionEntity)).collect(Collectors.toList());
    }

    @Override
    public Region save(Region region) throws BusinessException {
        Optional<RegionEntity> regionEntity = regionEntityRepository.findByNombre(region.getNombre());

        if(regionEntity.isPresent())
            throw new BusinessException(REGION_YA_REGISTRADA);

        DepartamentoEntity departamentoEntity = mapperUtils.mapperToDepartamentoEntity().apply(region.getDepartamento());
        RegionEntity regionCrear = regionEntityRepository.save(new RegionEntity(null, region.getNombre(), departamentoEntity));

        return mapperUtils.mapperToRegion().apply(regionCrear);
    }
}
