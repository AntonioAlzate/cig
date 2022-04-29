package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer>{

    Boolean existsByIdPersonaEntity(PersonaEntity idPersona);

    Optional<ClienteEntity> findByIdPersonaEntity(PersonaEntity idPersona);
}