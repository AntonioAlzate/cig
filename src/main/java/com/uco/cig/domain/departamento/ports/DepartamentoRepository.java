package com.uco.cig.domain.departamento.ports;

import com.uco.cig.domain.departamento.Departamento;

import java.util.List;

public interface DepartamentoRepository {

    List<Departamento> findAll(Integer idPais);
}
