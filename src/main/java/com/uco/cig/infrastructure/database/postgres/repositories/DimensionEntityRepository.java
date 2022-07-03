package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DimensionEntityRepository extends JpaRepository<DimensionEntity, Integer> {
}