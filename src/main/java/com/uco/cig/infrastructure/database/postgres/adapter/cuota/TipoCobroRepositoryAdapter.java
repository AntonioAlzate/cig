package com.uco.cig.infrastructure.database.postgres.adapter.cuota;

import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.tipocobro.ports.TipoCobroRepository;
import com.uco.cig.infrastructure.database.postgres.entities.TipoCobroEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.TipoCobroEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoCobroRepositoryAdapter implements TipoCobroRepository {

    private final MapperUtils mapperUtils;
    private final TipoCobroEntityRepository tipoCobroEntityRepository;

    public TipoCobroRepositoryAdapter(MapperUtils mapperUtils, TipoCobroEntityRepository tipoCobroEntityRepository) {
        this.mapperUtils = mapperUtils;
        this.tipoCobroEntityRepository = tipoCobroEntityRepository;
    }

    @Override
    public Optional<TipoCobro> findByNombre(String tipoCobro) {

        Optional<TipoCobroEntity> tipoCobroEntity = tipoCobroEntityRepository.findByNombre(tipoCobro);

        if(tipoCobroEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToTipoCobro().apply(tipoCobroEntity.get()));
    }
}
