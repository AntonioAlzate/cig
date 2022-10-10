package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CategoriaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.DimensionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface DimensionEntityRepository extends JpaRepository<DimensionEntity, Integer> {

    Optional<DimensionEntity> findByAnchoAndLargoAndIdCategoriaEntity(BigDecimal ancho, BigDecimal largo, CategoriaEntity idCategoria);

    List<DimensionEntity> findAllByIdCategoriaEntity(CategoriaEntity idCategoria);
}