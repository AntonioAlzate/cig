package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.TipoCobroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TipoCobroEntityRepository extends JpaRepository<TipoCobroEntity, Integer> {

    Optional<TipoCobroEntity> findByNombre(String nombre);
}