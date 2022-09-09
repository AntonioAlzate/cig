package com.uco.cig.infrastructure.database.postgres.adapter.zona;

import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ZonaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZonaRepositoryAdapter implements ZonaRepository {

    private final ZonaEntityRepository zonaEntityRepository;
    private final MapperUtils mapperUtils;

    public ZonaRepositoryAdapter(ZonaEntityRepository zonaEntityRepository, MapperUtils mapperUtils) {
        this.zonaEntityRepository = zonaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Zona> findAll() {

        List<ZonaEntity> zonaEntities = zonaEntityRepository.findAll();

        if(zonaEntities.isEmpty())
            return new ArrayList<>();

        return zonaEntities.stream().map(zonaEntity -> mapperUtils.mapperToZona().apply(zonaEntity)).collect(Collectors.toList());
    }
}
