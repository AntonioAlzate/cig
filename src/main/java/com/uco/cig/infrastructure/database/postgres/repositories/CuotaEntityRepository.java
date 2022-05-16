package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CuotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuotaEntityRepository extends JpaRepository<CuotaEntity, Integer> {
}