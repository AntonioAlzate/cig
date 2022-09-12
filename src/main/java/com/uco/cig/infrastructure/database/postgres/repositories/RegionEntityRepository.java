package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DepartamentoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionEntityRepository extends JpaRepository<RegionEntity, Integer> {

    List<RegionEntity> findAllByIdDepartamento(DepartamentoEntity idDepartamento);
    Optional<RegionEntity> findByNombre(String nombre);
}