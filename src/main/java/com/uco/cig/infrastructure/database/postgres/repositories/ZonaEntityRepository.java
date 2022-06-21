package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZonaEntityRepository extends JpaRepository<ZonaEntity, Integer> {
}