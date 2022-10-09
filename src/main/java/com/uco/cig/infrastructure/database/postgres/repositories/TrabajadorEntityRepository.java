package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrabajadorEntityRepository extends JpaRepository<TrabajadorEntity, Integer> {
    Boolean existsByIdPersona(PersonaEntity idPersona);

    Optional<TrabajadorEntity> findByIdPersona(PersonaEntity idPersona);
    Optional<TrabajadorEntity> findByCorreo(String correo);
}