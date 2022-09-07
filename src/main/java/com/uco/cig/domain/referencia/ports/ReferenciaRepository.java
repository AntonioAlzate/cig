package com.uco.cig.domain.referencia.ports;

import com.uco.cig.domain.referencia.Referencia;

import java.util.List;

public interface ReferenciaRepository {
    Referencia save(Referencia referencia);
    List<Referencia> findAllByCliente(Integer idCliente);
}
