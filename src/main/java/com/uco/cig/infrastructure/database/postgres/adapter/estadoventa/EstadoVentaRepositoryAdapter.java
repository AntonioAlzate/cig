package com.uco.cig.infrastructure.database.postgres.adapter.estadoventa;

import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.estado.ventas.ports.EstadoVentaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoVentaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoVentaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstadoVentaRepositoryAdapter implements EstadoVentaRepository {

    private final EstadoVentaEntityRepository estadoVentaEntityRepository;
    private final MapperUtils mapperUtils;

    public EstadoVentaRepositoryAdapter(EstadoVentaEntityRepository estadoVentaEntityRepository, MapperUtils mapperUtils) {
        this.estadoVentaEntityRepository = estadoVentaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<EstadoVenta> findByNombre(String nombre) {
        Optional<EstadoVentaEntity> estadoVentaEntity = estadoVentaEntityRepository.findByNombre(nombre);

        if(estadoVentaEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToEstadoVenta().apply(estadoVentaEntity.get()));
    }
}
