package com.uco.cig.infrastructure.database.postgres.adapter.liquidacion;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import com.uco.cig.infrastructure.database.postgres.entities.LiquidacionEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.LiquidacionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LiquidacionRepositoryAdapter implements LiquidacionRepository {

    private final LiquidacionEntityRepository liquidacionEntityRepository;
    private final MapperUtils mapperUtils;

    public LiquidacionRepositoryAdapter(LiquidacionEntityRepository liquidacionEntityRepository, MapperUtils mapperUtils) {
        this.liquidacionEntityRepository = liquidacionEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Liquidacion> findAll() {
        List<LiquidacionEntity> liquidacionEntities = liquidacionEntityRepository.findAll();

        return liquidacionEntities.stream().map(l -> mapperUtils.mapperToLiquidacion().apply(l)).collect(Collectors.toList());
    }
}
