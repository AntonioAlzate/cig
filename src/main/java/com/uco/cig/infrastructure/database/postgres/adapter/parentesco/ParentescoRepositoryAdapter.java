package com.uco.cig.infrastructure.database.postgres.adapter.parentesco;

import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.parentesco.ports.ParentescoRespository;
import com.uco.cig.infrastructure.database.postgres.entities.ParentescoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ParentescoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParentescoRepositoryAdapter implements ParentescoRespository {

    private final ParentescoEntityRepository parentescoEntityRepository;
    private final MapperUtils mapperUtils;

    public ParentescoRepositoryAdapter(ParentescoEntityRepository parentescoEntityRepository, MapperUtils mapperUtils) {
        this.parentescoEntityRepository = parentescoEntityRepository;
        this.mapperUtils = mapperUtils;
    }


    @Override
    public Optional<Parentesco> findById(Integer id) {
        Optional<ParentescoEntity> parentescoEntity = parentescoEntityRepository.findById(id);

        if(parentescoEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToParentesco().apply(parentescoEntity.get()));
    }
}
