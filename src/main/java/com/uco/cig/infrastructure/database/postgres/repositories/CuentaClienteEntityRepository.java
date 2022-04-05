package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CuentaClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaClienteEntityRepository extends JpaRepository<CuentaClienteEntity, Integer>{
}