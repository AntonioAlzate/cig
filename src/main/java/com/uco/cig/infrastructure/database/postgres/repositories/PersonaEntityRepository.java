package com.uco.cig.infrastructure.database.postgres.repositories;

import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaEntityRepository extends JpaRepository<PersonaEntity, Integer>{
    PersonaEntity findByIdentificacion(String identificacion);
}