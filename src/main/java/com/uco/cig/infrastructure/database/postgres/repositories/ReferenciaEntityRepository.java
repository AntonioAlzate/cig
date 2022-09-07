package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ReferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferenciaEntityRepository extends JpaRepository<ReferenciaEntity, Integer> {

    List<ReferenciaEntity> findAllByIdClienteEntity(ClienteEntity idCliente);
}