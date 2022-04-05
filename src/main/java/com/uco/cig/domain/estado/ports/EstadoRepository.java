package com.uco.cig.domain.estado.ports;

import com.uco.cig.domain.estado.Estado;

public interface EstadoRepository {

    Estado findByNombre(String nombre);
}