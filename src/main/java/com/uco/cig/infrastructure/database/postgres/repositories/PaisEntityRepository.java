package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaisEntityRepository extends JpaRepository<PaisEntity, Integer> {
}