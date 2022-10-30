package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.BarrioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BarrioEntityRepository extends JpaRepository<BarrioEntity, Integer>{

    List<BarrioEntity> findAllByIdZonaEntity_IdCiudad(CiudadEntity idCiudad);
    Optional<BarrioEntity> findByNombre(String nombre);
}