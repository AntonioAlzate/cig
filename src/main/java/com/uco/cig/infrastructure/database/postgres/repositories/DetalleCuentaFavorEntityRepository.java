package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DetalleCuentaFavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCuentaFavorEntityRepository extends JpaRepository<DetalleCuentaFavorEntity, Integer> {
}