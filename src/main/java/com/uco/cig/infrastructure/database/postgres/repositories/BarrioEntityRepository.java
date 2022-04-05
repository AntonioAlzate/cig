package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.BarrioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarrioEntityRepository extends JpaRepository<BarrioEntity, Integer>{
}