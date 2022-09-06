package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface DimensionEntityRepository extends JpaRepository<DimensionEntity, Integer> {

    DimensionEntity findByAnchoAndLargo(BigDecimal ancho, BigDecimal largo);
}