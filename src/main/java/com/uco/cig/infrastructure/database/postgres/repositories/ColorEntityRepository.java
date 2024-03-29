package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorEntityRepository extends JpaRepository<ColorEntity, Integer> {

    ColorEntity findByNombre(String nombreColor);
}