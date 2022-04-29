package com.uco.cig.domain.persona.ports;

import com.uco.cig.domain.persona.Persona;

import java.util.Optional;

public interface PersonaRepository {
    Optional<Persona> findByIdentificacion(String identificacion);
    Persona save(Persona persona);
}
