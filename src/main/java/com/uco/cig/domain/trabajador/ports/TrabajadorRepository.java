package com.uco.cig.domain.trabajador.ports;

import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.trabajador.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadorRepository {
    Trabajador save(Trabajador trabajador);

    boolean existsByIdPersona(Persona idPersona);

    Optional<Trabajador> findById(Integer id);

    Trabajador cambiarEstado(Integer idTrabajador, String estadoNuevo);

    Trabajador findByIdentificacion(String identificacion);

    List<Trabajador> findAll();

    Optional<Trabajador> findByCorreo(String correo);

    Trabajador update(Trabajador trabajador);
}
