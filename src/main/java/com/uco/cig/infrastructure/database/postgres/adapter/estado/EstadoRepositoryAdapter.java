package com.uco.cig.infrastructure.database.postgres.adapter.estado;

import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class EstadoRepositoryAdapter implements EstadoRepository {

    private final EstadoEntityRepository estadoEntityRepository;
    private final MapperUtils mapperUtils;

    public EstadoRepositoryAdapter(EstadoEntityRepository estadoEntityRepository, MapperUtils mapperUtils) {
        this.estadoEntityRepository = estadoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Estado findByNombre(String nombre) {
        EstadoEntity estadoEntity = estadoEntityRepository.findByNombre(nombre);

        return mapperUtils.mapperToEstado().apply(estadoEntity);
    }
}
