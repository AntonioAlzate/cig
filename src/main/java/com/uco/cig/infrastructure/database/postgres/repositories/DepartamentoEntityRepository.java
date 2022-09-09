package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DepartamentoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartamentoEntityRepository extends JpaRepository<DepartamentoEntity, Integer> {

    List<DepartamentoEntity> findAllByIdPais(PaisEntity idPais);
}