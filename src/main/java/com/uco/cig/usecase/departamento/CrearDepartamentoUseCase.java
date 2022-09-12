package com.uco.cig.usecase.departamento;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.departamento.ports.DepartamentoRepository;
import com.uco.cig.domain.pais.Pais;
import org.springframework.stereotype.Service;

@Service
public class CrearDepartamentoUseCase {

    private final DepartamentoRepository departamentoRepository;

    public CrearDepartamentoUseCase(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public Departamento crear(String nombreDepartamento, Pais pais) throws BusinessException {
        Departamento departamento = Departamento.nuevo(nombreDepartamento, pais);
        return departamentoRepository.save(departamento);
    }
}
