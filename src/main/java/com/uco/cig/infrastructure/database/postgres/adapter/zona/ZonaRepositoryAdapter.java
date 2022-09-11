package com.uco.cig.infrastructure.database.postgres.adapter.zona;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.zona.Zona;
import com.uco.cig.domain.zona.ports.ZonaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.CiudadEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.ZonaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZonaRepositoryAdapter implements ZonaRepository {

    private static final String CIUDAD_NO_ENCONTRADA = "La ciudad especificada no ha sido encontrada";

    private final ZonaEntityRepository zonaEntityRepository;
    private final CiudadEntityRepository ciudadEntityRepository;
    private final MapperUtils mapperUtils;

    public ZonaRepositoryAdapter(ZonaEntityRepository zonaEntityRepository, CiudadEntityRepository ciudadEntityRepository, MapperUtils mapperUtils) {
        this.zonaEntityRepository = zonaEntityRepository;
        this.ciudadEntityRepository = ciudadEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Zona> findAll() {

        List<ZonaEntity> zonaEntities = zonaEntityRepository.findAll();

        if(zonaEntities.isEmpty())
            return new ArrayList<>();

        return zonaEntities.stream().map(zonaEntity -> mapperUtils.mapperToZona().apply(zonaEntity)).collect(Collectors.toList());
    }

    @Override
    public List<Zona> findAllByIdCiudad(Integer idCiudad) {
        Optional<CiudadEntity> ciudadEntity = ciudadEntityRepository.findById(idCiudad);

        if(ciudadEntity.isEmpty())
            throw new NotFoundException(CIUDAD_NO_ENCONTRADA);

        List<ZonaEntity> zonaEntities = zonaEntityRepository.findAllByIdCiudad(ciudadEntity.get());

        if(zonaEntities.isEmpty())
            return new ArrayList<>();

        return zonaEntities.stream().map(zonaEntity -> mapperUtils.mapperToZona().apply(zonaEntity)).collect(Collectors.toList());
    }
}
