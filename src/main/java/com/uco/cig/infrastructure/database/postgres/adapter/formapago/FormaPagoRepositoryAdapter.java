package com.uco.cig.infrastructure.database.postgres.adapter.formapago;

import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.formapago.ports.FormaPagoRepository;
import com.uco.cig.infrastructure.database.postgres.entities.FormaPagoEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.FormaPagoEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormaPagoRepositoryAdapter implements FormaPagoRepository {

    private final FormaPagoEntityRepository formaPagoEntityRepository;
    private final MapperUtils mapperUtils;

    public FormaPagoRepositoryAdapter(FormaPagoEntityRepository formaPagoEntityRepository, MapperUtils mapperUtils) {
        this.formaPagoEntityRepository = formaPagoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<FormaPago> findById(Integer id) {
        Optional<FormaPagoEntity> formaPagoEntity = formaPagoEntityRepository.findById(id);

        if (formaPagoEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToFormaPago().apply(formaPagoEntity.get()));
    }
}
