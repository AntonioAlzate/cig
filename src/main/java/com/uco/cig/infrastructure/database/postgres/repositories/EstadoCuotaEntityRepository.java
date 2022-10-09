package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadoCuotaEntityRepository extends JpaRepository<EstadoCuotaEntity, Integer> {

    Optional<EstadoCuotaEntity> findByNombre(String nombre);
}