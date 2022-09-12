package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaisEntityRepository extends JpaRepository<PaisEntity, Integer> {

    Optional<PaisEntity> findByNombre(String nombre);
}