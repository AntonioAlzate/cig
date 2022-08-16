package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EntradaCuentaFavorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaCuentaFavorEntityRepository extends JpaRepository<EntradaCuentaFavorEntity, Integer> {
}