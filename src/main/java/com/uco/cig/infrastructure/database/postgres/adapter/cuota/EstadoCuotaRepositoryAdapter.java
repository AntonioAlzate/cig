package com.uco.cig.infrastructure.database.postgres.adapter.cuota;

import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.estado.cuota.ports.EstadoCuotaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuotaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoCuotaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoCuotaRepositoryAdapter implements EstadoCuotaRepository {

    private final MapperUtils mapperUtils;
    private final EstadoCuotaEntityRepository estadoCuotaEntityRepository;

    public EstadoCuotaRepositoryAdapter(MapperUtils mapperUtils, EstadoCuotaEntityRepository estadoCuotaEntityRepository) {
        this.mapperUtils = mapperUtils;
        this.estadoCuotaEntityRepository = estadoCuotaEntityRepository;
    }

    @Override
    public Optional<EstadoCuota> findByNombre(String nombre) {

        Optional<EstadoCuotaEntity> estadoCuotaEntity = estadoCuotaEntityRepository.findByNombre(nombre);

        if(estadoCuotaEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToEstadoCuota().apply(estadoCuotaEntity.get()));
    }
}
