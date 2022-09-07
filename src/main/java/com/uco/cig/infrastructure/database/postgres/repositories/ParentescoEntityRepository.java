package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ParentescoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentescoEntityRepository extends JpaRepository<ParentescoEntity, Integer> {
}