package com.uco.cig.domain.modalidad.ports;

import com.uco.cig.domain.modalidad.Modalidad;

import java.util.Optional;

public interface ModalidadRepository {

    Optional<Modalidad> findById(Integer id);
    Optional<Modalidad> findByNombre(String nombre);
}
