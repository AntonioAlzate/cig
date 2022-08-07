package com.uco.cig.infrastructure.database.postgres.adapter.detalleventa;

import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.detalle.venta.ports.DetalleVentaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.DetalleVentaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DetalleVentaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleVentaRepositoryAdapter implements DetalleVentaRepository {

    private final DetalleVentaEntityRepository detalleVentaEntityRepository;
    private final MapperUtils mapperUtils;

    public DetalleVentaRepositoryAdapter(DetalleVentaEntityRepository detalleVentaEntityRepository, MapperUtils mapperUtils) {
        this.detalleVentaEntityRepository = detalleVentaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<DetalleVenta> saveAll(List<DetalleVenta> detalles) {
        List<DetalleVentaEntity> detalleVentaEntities =
                detalles.stream().map(detalleVenta -> mapperUtils.mappertoDetalleVentaEntity().apply(detalleVenta)).collect(Collectors.toList());

        detalleVentaEntities = detalleVentaEntityRepository.saveAll(detalleVentaEntities);

        return detalleVentaEntities.stream().map(detalleVentaEntity -> mapperUtils.mapperToDetalleVenta().apply(detalleVentaEntity)).collect(Collectors.toList());
    }
}
