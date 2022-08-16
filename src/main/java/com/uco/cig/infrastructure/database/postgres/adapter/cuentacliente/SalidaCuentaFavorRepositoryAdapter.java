package com.uco.cig.infrastructure.database.postgres.adapter.cuentacliente;

import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.salida.SalidaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.salida.SalidaCuentaFavorRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EntradaCuentaFavorEntity;
import com.uco.cig.infrastructure.database.postgres.entities.SalidaCuentaFavorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EntradaCuentaFavorEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.SalidaCuentaFavorEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class SalidaCuentaFavorRepositoryAdapter implements SalidaCuentaFavorRepository {

    private final SalidaCuentaFavorEntityRepository salidaCuentaFavorEntityRepository;
    private final MapperUtils mapperUtils;

    public SalidaCuentaFavorRepositoryAdapter(SalidaCuentaFavorEntityRepository salidaCuentaFavorEntityRepository, MapperUtils mapperUtils) {
        this.salidaCuentaFavorEntityRepository = salidaCuentaFavorEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public SalidaCuentaFavor save(SalidaCuentaFavor salidaCuentaFavor) {
        SalidaCuentaFavorEntity salidaCuentaFavorEntity = mapperUtils.mappertoSalidaCuentaFavorEntity().apply(salidaCuentaFavor);
        salidaCuentaFavorEntity = salidaCuentaFavorEntityRepository.save(salidaCuentaFavorEntity);
        return mapperUtils.mapperToSalidaCuentaFavor().apply(salidaCuentaFavorEntity);
    }
}
