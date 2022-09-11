package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.CiudadEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZonaEntityRepository extends JpaRepository<ZonaEntity, Integer> {

    List<ZonaEntity> findAllByIdCiudad(CiudadEntity idCiudad);
}