package com.uco.cig.infrastructure.database.postgres.adapter.referencia;

import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.domain.referencia.ports.ReferenciaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ReferenciaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.ReferenciaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReferenciaRepositoryAdapter implements ReferenciaRepository {

    private final ReferenciaEntityRepository referenciaEntityRepository;
    private final MapperUtils mapperUtils;

    public ReferenciaRepositoryAdapter(ReferenciaEntityRepository referenciaEntityRepository, MapperUtils mapperUtils) {
        this.referenciaEntityRepository = referenciaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Referencia save(Referencia referencia) {
        ReferenciaEntity referenciaEntity = mapperUtils.mappertoReferenciaEntity().apply(referencia);

        referenciaEntity = referenciaEntityRepository.save(referenciaEntity);

        return mapperUtils.mapperToReferencia().apply(referenciaEntity);
    }

    @Override
    public List<Referencia> findAllByCliente(Integer idCliente) {
        ClienteEntity cliente = new ClienteEntity(idCliente, null, null, null);
        List<ReferenciaEntity> referenciaEntities = referenciaEntityRepository.findAllByIdClienteEntity(cliente);

        return referenciaEntities.stream().map(referenciaEntity -> mapperUtils.mapperToReferencia().apply(referenciaEntity)).collect(Collectors.toList());
    }
}
