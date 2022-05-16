package com.uco.cig.infrastructure.database.postgres.adapter.cuota;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CuotaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CuotaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuotaRepositoryAdapter implements CuotaRepository {

    private final CuotaEntityRepository cuotaEntityRepository;
    private final MapperUtils mapperUtils;

    public CuotaRepositoryAdapter(CuotaEntityRepository cuotaEntityRepository, MapperUtils mapperUtils) {
        this.cuotaEntityRepository = cuotaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Cuota> findAll() {
        List<CuotaEntity> cuotaEntities = cuotaEntityRepository.findAll();

        return cuotaEntities.stream().map(c -> mapperUtils.mapperToCuota().apply(c)).collect(Collectors.toList());
    }
}
