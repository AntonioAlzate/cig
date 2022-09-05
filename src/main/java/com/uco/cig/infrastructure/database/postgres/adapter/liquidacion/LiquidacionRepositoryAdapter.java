package com.uco.cig.infrastructure.database.postgres.adapter.liquidacion;

import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.infrastructure.database.postgres.entities.LiquidacionEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.LiquidacionEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public Liquidacion save(Liquidacion liquidacion) {
        LiquidacionEntity liquidacionEntity = mapperUtils.mappertoLiquidacionEntity().apply(liquidacion);

        liquidacionEntity = liquidacionEntityRepository.save(liquidacionEntity);
        return mapperUtils.mapperToLiquidacion().apply(liquidacionEntity);
    }

    @Override
    public List<Liquidacion> findAllByTrabajador(Trabajador trabajador) {

        TrabajadorEntity trabajadorEntity = mapperUtils.mappertoTrabajadorEntity().apply(trabajador);

        List<LiquidacionEntity> liquidacionEntities = liquidacionEntityRepository.findAllByIdTrabajadorEntity(trabajadorEntity);

        if(liquidacionEntities.isEmpty())
            return new ArrayList<>();

        liquidacionEntities.forEach(liquidacionEntity -> liquidacionEntity.setFecha(liquidacionEntity.getFecha().plusHours(5)));

        return liquidacionEntities.stream().map(liquidacionEntity -> mapperUtils.mapperToLiquidacion().apply(liquidacionEntity)).collect(Collectors.toList());
    }

}
