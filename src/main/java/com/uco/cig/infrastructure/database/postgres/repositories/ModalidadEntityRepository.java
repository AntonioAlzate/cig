package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ModalidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ModalidadEntityRepository extends JpaRepository<ModalidadEntity, Integer> {
    Optional<ModalidadEntity> findByNombre(String nombre);
}