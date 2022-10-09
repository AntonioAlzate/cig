package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoLiquidacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoLiquidacionEntityRepository extends JpaRepository<EstadoLiquidacionEntity, Integer> {

    Optional<EstadoLiquidacionEntity> findByNombre(String nombre);
}