package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.LiquidacionEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LiquidacionEntityRepository extends JpaRepository<LiquidacionEntity, Integer> {
    List<LiquidacionEntity> findAllByIdTrabajadorEntity(TrabajadorEntity idTrabajador);
}