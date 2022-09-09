package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CiudadEntityRepository extends JpaRepository<CiudadEntity, Integer> {

    List<CiudadEntity> findAllByIdRegionEntity(RegionEntity idRegion);
}