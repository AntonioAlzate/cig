package com.uco.cig.infrastructure.database.postgres.adapter.trabajador;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import com.uco.cig.infrastructure.database.postgres.entities.EstadoEntity;
import com.uco.cig.infrastructure.database.postgres.entities.PersonaEntity;
import com.uco.cig.infrastructure.database.postgres.entities.TrabajadorEntity;
import com.uco.cig.infrastructure.database.postgres.repositories.EstadoEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.PersonaEntityRepository;
import com.uco.cig.infrastructure.database.postgres.repositories.TrabajadorEntityRepository;
import com.uco.cig.shared.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TrabajadorRepositoryAdapter implements TrabajadorRepository {

    private static final String TRABAJADOR_CON_IDENTIFICACION_NO_EXISTE = "No se encontro un trabajador asociado a la identificación especificada";
    private static final String PERSONA_NO_ENCONTRADA = "La identificación que ha ingresa no coincide con la de ninguna persona en el sistema";

    private final TrabajadorEntityRepository trabajadorEntityRepository;
    private final PersonaEntityRepository personaEntityRepository;
    private final EstadoEntityRepository estadoEntityRepository;
    private final MapperUtils mapperUtils;

    public TrabajadorRepositoryAdapter(TrabajadorEntityRepository trabajadorEntityRepository, PersonaEntityRepository personaEntityRepository, EstadoEntityRepository estadoEntityRepository, MapperUtils mapperUtils) {
        this.trabajadorEntityRepository = trabajadorEntityRepository;
        this.personaEntityRepository = personaEntityRepository;
        this.estadoEntityRepository = estadoEntityRepository;
        this.mapperUtils = mapperUtils;
    }

    @Override
    public Trabajador save(Trabajador trabajador) {

        Optional<PersonaEntity> personaValidar = personaEntityRepository.findByIdentificacion(trabajador.getPersona().getIdentificacion());

        Persona persona = trabajador.getPersona();
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(persona);

        if(personaValidar.isEmpty()){
            personaEntity = personaEntityRepository.save(personaEntity);
        } else {
            personaEntity = personaValidar.get();
        }

        TrabajadorEntity trabajadorEntity =  mapperUtils.mappertoTrabajadorEntity().apply(trabajador);
        trabajadorEntity.setIdPersona(personaEntity);

        trabajadorEntity = trabajadorEntityRepository.save(trabajadorEntity);

        return mapperUtils.mapperToTrabajador().apply(trabajadorEntity);
    }

    @Override
    public boolean existsByIdPersona(Persona idPersona) {
        PersonaEntity personaEntity = mapperUtils.mappertoPersonaEntity().apply(idPersona);
        return trabajadorEntityRepository.existsByIdPersona(personaEntity);
    }

    @Override
    public Optional<Trabajador> findById(Integer id) {
        Optional<TrabajadorEntity> trabajadorEntity = trabajadorEntityRepository.findById(id);

        if (trabajadorEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToTrabajador().apply(trabajadorEntity.get()));
    }

    @Override
    public Trabajador cambiarEstado(Integer idTrabajador, String estado) {
        Optional<TrabajadorEntity> trabajadorEntity = trabajadorEntityRepository.findById(idTrabajador);

        if(trabajadorEntity.isEmpty()){
            throw new NotFoundException(TRABAJADOR_CON_IDENTIFICACION_NO_EXISTE);
        }

        EstadoEntity estadoEntity = estadoEntityRepository.findByNombre(estado);
        trabajadorEntity.get().setIdEstado(estadoEntity);

        return mapperUtils.mapperToTrabajador().apply(trabajadorEntityRepository.save(trabajadorEntity.get()));
    }

    @Override
    public Trabajador findByIdentificacion(String identificacion) {
        Optional<PersonaEntity> personaEntity = personaEntityRepository.findByIdentificacion(identificacion);
        Optional<TrabajadorEntity> trabajadorEntity;

        if (personaEntity.isEmpty()) {
            throw new NotFoundException(PERSONA_NO_ENCONTRADA);
        }

        trabajadorEntity = trabajadorEntityRepository.findByIdPersona(personaEntity.get());

        if (trabajadorEntity.isEmpty()) {
            throw new NotFoundException(TRABAJADOR_CON_IDENTIFICACION_NO_EXISTE);
        }

        return mapperUtils.mapperToTrabajador().apply(trabajadorEntity.get());
    }

    @Override
    public List<Trabajador> findAll() {
        List<TrabajadorEntity> trabajadores = trabajadorEntityRepository.findAll();

        return trabajadores.stream().map(x -> mapperUtils.mapperToTrabajador().apply(x)).collect(Collectors.toList());
    }

    @Override
    public Optional<Trabajador> findByCorreo(String correo) {
        Optional<TrabajadorEntity> trabajadorEntity = trabajadorEntityRepository.findByCorreo(correo);

        if(trabajadorEntity.isEmpty())
            return Optional.empty();

        return Optional.of(mapperUtils.mapperToTrabajador().apply(trabajadorEntity.get()));
    }

    @Override
    public Trabajador update(Trabajador trabajador) {
        TrabajadorEntity trabajadorEntity = mapperUtils.mappertoTrabajadorEntity().apply(trabajador);

        trabajadorEntity.setIdPersona(personaEntityRepository.save(trabajadorEntity.getIdPersona()));

        trabajadorEntity = trabajadorEntityRepository.save(trabajadorEntity);

        return mapperUtils.mapperToTrabajador().apply(trabajadorEntity);
    }
}
