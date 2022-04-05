package com.uco.cig.domain.persona.ports;

import com.uco.cig.domain.persona.Persona;

public interface PersonaRepository {

    Persona save(Persona persona);
}
