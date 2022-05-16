package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ProductoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoEntityRepository extends JpaRepository<ProductoEntity, Integer> {
}