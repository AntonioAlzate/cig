package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.SalidaCuentaFavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalidaCuentaFavorEntityRepository extends JpaRepository<SalidaCuentaFavorEntity, Integer> {
}