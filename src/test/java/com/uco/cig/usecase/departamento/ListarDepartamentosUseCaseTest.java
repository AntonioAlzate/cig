package com.uco.cig.usecase.departamento;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.departamento.ports.DepartamentoRepository;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.generate.DepartamentosHelper;
import com.uco.cig.generate.PaisHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarDepartamentosUseCaseTest {

    DepartamentoRepository departamentoRepository;

    ListarDepartamentosUseCase listarDepartamentosUseCase;

    @BeforeEach
    public void setup(){
        departamentoRepository = mock(DepartamentoRepository.class);
        listarDepartamentosUseCase = new ListarDepartamentosUseCase(departamentoRepository);
    }

    @Test
    public void listarDepartamentosUseCaseTest() throws BusinessException {
        Pais pais = PaisHelper.crearPais();
        List<Departamento> departamentos = Arrays.asList(
                DepartamentosHelper.crearDepartamento(pais),
                DepartamentosHelper.crearDepartamento(pais),
                DepartamentosHelper.crearDepartamento(pais)
        );

        when(departamentoRepository.findAll(pais.getId())).thenReturn(departamentos);

        List<Departamento> result = listarDepartamentosUseCase.listar(pais.getId());
        assertEquals(3, result.size());
        assertEquals(departamentos, result);
        Mockito.verify(departamentoRepository).findAll(pais.getId());
    }

}