package com.uco.cig.infrastructure.database.postgres.adapter.modalidad;

import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.modalidad.ports.ModalidadRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ModalidadEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ModalidadEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModalidadRepositoryAdapter implements ModalidadRepository {

    private final ModalidadEntityRepository modalidadEntityRepository;
    private final MapperUtils mapperUtils;

    public ModalidadRepositoryAdapter(ModalidadEntityRepository modalidadEntityRepository, MapperUtils mapperUtils) {
        this.modalidadEntityRepository = modalidadEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Modalidad> findById(Integer id) {
        Optional<ModalidadEntity> modalidadEntity = modalidadEntityRepository.findById(id);

        if (modalidadEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToModalidad().apply(modalidadEntity.get()));
    }

    @Override
    public Optional<Modalidad> findByNombre(String nombre) {
        Optional<ModalidadEntity> modalidadEntity = modalidadEntityRepository.findByNombre(nombre);

        if (modalidadEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToModalidad().apply(modalidadEntity.get()));
    }
}
