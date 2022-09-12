package com.uco.cig.infrastructure.database.postgres.adapter.pais;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.pais.ports.PaisRepository;
import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.PaisEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaisRepositoryAdapter implements PaisRepository {

    private static final String PAIS_YA_REGISTRADO = "Ya se encontra en el sistema un país con el mismo nombre";

    private final PaisEntityRepository paisEntityRepository;
    private final MapperUtils mapperUtils;

    public PaisRepositoryAdapter(PaisEntityRepository paisEntityRepository, MapperUtils mapperUtils) {
        this.paisEntityRepository = paisEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Pais> findAll() {
        List<PaisEntity> paisEntities = paisEntityRepository.findAll();

        if(paisEntities.isEmpty())
            return new ArrayList<>();

        return paisEntities.stream().map(paisEntity -> mapperUtils.mapperToPais().apply(paisEntity)).collect(Collectors.toList());
    }

    @Override
    public Pais save(Pais pais) throws BusinessException {
        Optional<PaisEntity> paisEntity = paisEntityRepository.findByNombre(pais.getNombre());

        if(paisEntity.isPresent())
            throw new BusinessException(PAIS_YA_REGISTRADO);

        PaisEntity paisCreate = paisEntityRepository.save(new PaisEntity(null, pais.getNombre()));

        return mapperUtils.mapperToPais().apply(paisCreate);
    }
}
