package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoVentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoVentaEntityRepository extends JpaRepository<EstadoVentaEntity, Integer> {

    Optional<EstadoVentaEntity> findByNombre(String nombre);
}