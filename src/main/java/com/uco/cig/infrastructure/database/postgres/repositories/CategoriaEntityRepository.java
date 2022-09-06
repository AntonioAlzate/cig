package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaEntityRepository extends JpaRepository<CategoriaEntity, Integer> {
    CategoriaEntity findByNombre(String nombreCategoria);
}