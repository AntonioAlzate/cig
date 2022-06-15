package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.RegistroDespachoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistroDespachoEntityRepository extends JpaRepository<RegistroDespachoEntity, Integer> {
}