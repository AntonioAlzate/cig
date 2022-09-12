package com.uco.cig.domain.departamento.ports;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.pais.Pais;

import java.util.List;
import java.util.Optional;

public interface DepartamentoRepository {

    List<Departamento> findAll(Integer idPais);
    Departamento save(Departamento departamento) throws BusinessException;
}
