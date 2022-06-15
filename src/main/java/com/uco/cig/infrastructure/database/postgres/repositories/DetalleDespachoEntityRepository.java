package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DetalleDespachoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleDespachoEntityRepository extends JpaRepository<DetalleDespachoEntity, Integer> {
}