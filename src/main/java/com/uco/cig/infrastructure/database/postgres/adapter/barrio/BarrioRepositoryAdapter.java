package com.uco.cig.infrastructure.database.postgres.adapter.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.infrastructure.database.postgres.entities.BarrioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.BarrioEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BarrioRepositoryAdapter implements BarrioRepository {

    private final BarrioEntityRepository barrioEntityRepository;
    private final MapperUtils mapperUtils;

    public BarrioRepositoryAdapter(BarrioEntityRepository barrioEntityRepository, MapperUtils mapperUtils) {
        this.barrioEntityRepository = barrioEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Barrio> findById(Integer id) {
        Optional<BarrioEntity> barrioEntity = barrioEntityRepository.findById(id);

        if(barrioEntity.isEmpty()){
            return Optional.empty();
        }

        return Optional.of(mapperUtils.mapperToBarrio().apply(barrioEntity.get()));
    }

    @Override
    public List<Barrio> findAll() {
        List<BarrioEntity> barrioEntities = barrioEntityRepository.findAll();

        if(barrioEntities.isEmpty())
            return new ArrayList<>();

        return barrioEntities.stream().map(barrioEntity -> mapperUtils.mapperToBarrio().apply(barrioEntity)).collect(Collectors.toList());
    }
}
