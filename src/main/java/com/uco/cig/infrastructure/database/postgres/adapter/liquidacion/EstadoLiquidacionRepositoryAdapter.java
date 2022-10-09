package com.uco.cig.infrastructure.database.postgres.adapter.liquidacion;

import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.estado.liquidacion.ports.EstadoLiquidacionRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoLiquidacionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoLiquidacionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoLiquidacionRepositoryAdapter implements EstadoLiquidacionRepository {

    private final MapperUtils mapperUtils;
    private final EstadoLiquidacionEntityRepository estadoLiquidacionEntityRepository;

    public EstadoLiquidacionRepositoryAdapter(MapperUtils mapperUtils, EstadoLiquidacionEntityRepository estadoLiquidacionEntityRepository) {
        this.mapperUtils = mapperUtils;
        this.estadoLiquidacionEntityRepository = estadoLiquidacionEntityRepository;
    }

    @Override
    public Optional<EstadoLiquidacion> findByNombre(String nombre) {

        Optional<EstadoLiquidacionEntity> estadoLiquidacionEntity = estadoLiquidacionEntityRepository.findByNombre(nombre);

        if(estadoLiquidacionEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToEstadoLiquidacion().apply(estadoLiquidacionEntity.get()));
    }
}
