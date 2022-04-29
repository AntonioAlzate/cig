package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuentaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoCuentaClienteEntityRepository extends JpaRepository<EstadoCuentaClienteEntity, Integer> {
    Optional<EstadoCuentaClienteEntity> findByEstado(String estado);
}