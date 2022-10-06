package com.uco.cig.infrastructure.database.postgres.adapter.persona;

import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.PersonaEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonaRepositoryAdapter implements PersonaRepository {

    private final PersonaEntityRepository personaEntityRepository;
    private final MapperUtils mapperUtils;

    public PersonaRepositoryAdapter(PersonaEntityRepository personaEntityRepository, MapperUtils mapperUtils) {
        this.personaEntityRepository = personaEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Optional<Persona> findByIdentificacion(String identificacion) {
        PersonaEntity personaEntity = personaEntityRepository.findByIdentificacion(identificacion);
        return personaEntity != null ? Optional.of(mapperUtils.mapperToPersona().apply(personaEntity)) : Optional.empty();
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(persona);
        personaEntity = personaEntityRepository.save(personaEntity);
        persona = mapperUtils.mapperToPersona().apply(personaEntity);
        return persona;
    }
}
