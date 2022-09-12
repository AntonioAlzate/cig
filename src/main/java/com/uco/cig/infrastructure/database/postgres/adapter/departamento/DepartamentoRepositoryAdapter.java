package com.uco.cig.infrastructure.database.postgres.adapter.departamento;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.departamento.ports.DepartamentoRepository;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.infrastructure.database.postgres.entities.DepartamentoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.DepartamentoEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.PaisEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartamentoRepositoryAdapter implements DepartamentoRepository {

    private static final String PAIS_NO_ENCONTRADO = "El país no ha sido encontrado en el sistema";
    private static final String DEPARTAMENTO_YA_CREADO = "Ya existe un departamento con el mismo nombre en el sistema";

    private final DepartamentoEntityRepository departamentoEntityRepository;
    private final PaisEntityRepository paisEntityRepository;
    private final MapperUtils mapperUtils;

    public DepartamentoRepositoryAdapter(DepartamentoEntityRepository departamentoEntityRepository, PaisEntityRepository paisEntityRepository, MapperUtils mapperUtils) {
        this.departamentoEntityRepository = departamentoEntityRepository;
        this.paisEntityRepository = paisEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public List<Departamento> findAll(Integer idPais) {
        Optional<PaisEntity> pais = paisEntityRepository.findById(idPais);

        if(pais.isEmpty())
            throw new NotFoundException(PAIS_NO_ENCONTRADO);

        List<DepartamentoEntity> departamentoEntities = departamentoEntityRepository.findAllByIdPais(pais.get());

        if(departamentoEntities.isEmpty())
            return new ArrayList<>();

        return departamentoEntities.stream().map(departamentoEntity -> mapperUtils.mapperToDepartamento().apply(departamentoEntity)).collect(Collectors.toList());
    }

    @Override
    public Departamento save(Departamento departamento) throws BusinessException {
        Optional<DepartamentoEntity> departamentoEntity = departamentoEntityRepository.findByNombre(departamento.getNombre());

        if(departamentoEntity.isPresent())
            throw new BusinessException(DEPARTAMENTO_YA_CREADO);

        PaisEntity paisEntity = mapperUtils.mapperToPaisEntity().apply(departamento.getPais());
        DepartamentoEntity departamentoCrear = departamentoEntityRepository.save(new DepartamentoEntity(null, departamento.getNombre(), paisEntity));

        return mapperUtils.mapperToDepartamento().apply(departamentoCrear);
    }
}
