package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoCuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCuotaEntityRepository extends JpaRepository<EstadoCuotaEntity, Integer> {
}