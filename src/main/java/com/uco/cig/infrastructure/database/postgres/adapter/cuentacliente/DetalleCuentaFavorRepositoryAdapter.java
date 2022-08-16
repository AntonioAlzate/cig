package com.uco.cig.infrastructure.database.postgres.adapter.cuentacliente;

import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.ports.DetalleCuentaFavorRepository;
import com.uco.cig.infrastructure.database.postgres.entities.DetalleCuentaFavorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DetalleCuentaFavorEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class DetalleCuentaFavorRepositoryAdapter implements DetalleCuentaFavorRepository {

    private final DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository;
    private final MapperUtils mapperUtils;

    public DetalleCuentaFavorRepositoryAdapter(DetalleCuentaFavorEntityRepository detalleCuentaFavorEntityRepository, MapperUtils mapperUtils) {
        this.detalleCuentaFavorEntityRepository = detalleCuentaFavorEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public DetalleCuentaFavor save(DetalleCuentaFavor detalleCuentaFavor) {
        DetalleCuentaFavorEntity detalleCuentaFavorEntity = mapperUtils.mappertoDetalleCuentaFavorEntity().apply(detalleCuentaFavor);

        detalleCuentaFavorEntity = detalleCuentaFavorEntityRepository.save(detalleCuentaFavorEntity);

        return mapperUtils.mapperToDetalleCuentaFavor().apply(detalleCuentaFavorEntity);
    }
}
