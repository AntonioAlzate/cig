package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoEntityRepository extends JpaRepository<EstadoEntity, Integer>{
    EstadoEntity findByNombre(String nombre);
}