package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.ClienteEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteEntityRepository extends JpaRepository<ClienteEntity, Integer>{

    Boolean existsByIdPersonaEntity(PersonaEntity idPersona);

    Optional<ClienteEntity> findByIdPersonaEntity(PersonaEntity idPersona);

    List<ClienteEntity> findClienteEntitiesByIdPersonaEntity_IdBarrio_IdZonaEntity(ZonaEntity idZona);
}