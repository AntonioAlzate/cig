package com.uco.cig.usecase.departamento;

import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.departamento.ports.DepartamentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarDepartamentosUseCase {

    private final DepartamentoRepository departamentoRepository;

    public ListarDepartamentosUseCase(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    public List<Departamento> listar(Integer idPais){
        return departamentoRepository.findAll(idPais);
    }
}
