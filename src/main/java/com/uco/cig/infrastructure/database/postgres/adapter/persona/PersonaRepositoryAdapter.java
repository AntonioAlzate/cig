package com.uco.cig.infrastructure.database.postgres.adapter.persona;

import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.infrastructure.database.postgres.entities.BarrioEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.PersonaEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PersonaRepositoryAdapter implements PersonaRepository {

    private final PersonaEntityRepository personaEntityRepository;

    public PersonaRepositoryAdapter(PersonaEntityRepository personaEntityRepository) {
        this.personaEntityRepository = personaEntityRepository;
    }

    @Override
    public Persona save(Persona persona) {
        PersonaEntity personaEntity = new PersonaEntity();
        BeanUtils.copyProperties(persona, personaEntity);
        personaEntity.setIdBarrio(new BarrioEntity());
        personaEntity = personaEntityRepository.save(personaEntity);
        BeanUtils.copyProperties(personaEntity, persona);
        return persona;
    }
}
