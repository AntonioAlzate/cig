package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.FormaPagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FormaPagoEntityRepository extends JpaRepository<FormaPagoEntity, Integer> {
}