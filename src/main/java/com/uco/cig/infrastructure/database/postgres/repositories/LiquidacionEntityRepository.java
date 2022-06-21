package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.LiquidacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiquidacionEntityRepository extends JpaRepository<LiquidacionEntity, Integer> {
}