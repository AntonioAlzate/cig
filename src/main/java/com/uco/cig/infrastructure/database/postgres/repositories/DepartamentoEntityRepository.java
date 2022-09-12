package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.DepartamentoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DepartamentoEntityRepository extends JpaRepository<DepartamentoEntity, Integer> {

    List<DepartamentoEntity> findAllByIdPais(PaisEntity idPais);

    Optional<DepartamentoEntity> findByNombre(String nombre);
}