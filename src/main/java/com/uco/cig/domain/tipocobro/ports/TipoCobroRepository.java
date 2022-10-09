package com.uco.cig.domain.tipocobro.ports;

import com.uco.cig.domain.tipocobro.TipoCobro;

import java.util.Optional;

public interface TipoCobroRepository {
    Optional<TipoCobro> findByNombre(String tipoCobro);
}
