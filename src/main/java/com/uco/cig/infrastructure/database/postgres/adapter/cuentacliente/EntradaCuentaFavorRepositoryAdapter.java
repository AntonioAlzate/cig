package com.uco.cig.infrastructure.database.postgres.adapter.cuentacliente;

import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavorRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EntradaCuentaFavorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EntradaCuentaFavorEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

@Service
public class EntradaCuentaFavorRepositoryAdapter implements EntradaCuentaFavorRepository {

    private final EntradaCuentaFavorEntityRepository entradaCuentaFavorEntityRepository;
    private final MapperUtils mapperUtils;

    public EntradaCuentaFavorRepositoryAdapter(EntradaCuentaFavorEntityRepository entradaCuentaFavorEntityRepository, MapperUtils mapperUtils) {
        this.entradaCuentaFavorEntityRepository = entradaCuentaFavorEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public EntradaCuentaFavor save(EntradaCuentaFavor entradaCuentaFavor) {
        EntradaCuentaFavorEntity entradaCuentaFavorEntity = mapperUtils.mappertoEntradaCuentaFavorEntity().apply(entradaCuentaFavor);
        entradaCuentaFavorEntity = entradaCuentaFavorEntityRepository.save(entradaCuentaFavorEntity);
        return mapperUtils.mapperToEntradaCuentaFavor().apply(entradaCuentaFavorEntity);
    }
}
