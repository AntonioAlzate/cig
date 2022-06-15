package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.EstadoDespachoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoDespachoEntityRepository extends JpaRepository<EstadoDespachoEntity, Integer> {
}