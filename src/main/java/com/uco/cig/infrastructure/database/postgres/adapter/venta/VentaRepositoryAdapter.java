package com.uco.cig.infrastructure.database.postgres.adapter.venta;

import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.VentaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaRepositoryAdapter implements VentaRepository {

    private final VentaEntityRepository ventaEntityRepository;
    private final MapperUtils mapperUtils;

    public VentaRepositoryAdapter(VentaEntityRepository ventaEntityRepository, MapperUtils mapperUtils) {
        this.ventaEntityRepository = ventaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Venta> findAll() {
        List<VentaEntity> ventaEntities = ventaEntityRepository.findAll();

        return ventaEntities.stream().map(v -> mapperUtils.mapperToVenta().apply(v)).collect(Collectors.toList());
    }
}
