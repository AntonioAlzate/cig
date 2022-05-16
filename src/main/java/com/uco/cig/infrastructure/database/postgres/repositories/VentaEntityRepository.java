package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.VentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VentaEntityRepository extends JpaRepository<VentaEntity, Integer> {
}