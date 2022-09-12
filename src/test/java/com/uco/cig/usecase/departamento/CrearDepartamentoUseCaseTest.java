package com.uco.cig.usecase.departamento;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.departamento.Departamento;
import com.uco.cig.domain.departamento.ports.DepartamentoRepository;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.generate.PaisHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class CrearDepartamentoUseCaseTest {

    DepartamentoRepository departamentoRepository;

    CrearDepartamentoUseCase crearDepartamentoUseCase;

    @BeforeEach
    public void setup(){
        departamentoRepository = mock(DepartamentoRepository.class);
        crearDepartamentoUseCase = new CrearDepartamentoUseCase(departamentoRepository);
    }

    @Test
    public void crearDepartamentoTest() throws BusinessException {
        Pais pais = PaisHelper.crearPais();
        String nombreDepartamento = "DEPARTAMENTO_PRUEBA";

        Departamento departamento = Departamento.nuevo(nombreDepartamento, pais);

        when(departamentoRepository.save(any())).thenReturn(departamento);

        Departamento result = crearDepartamentoUseCase.crear(nombreDepartamento,pais);

        assertEquals(departamento, result);
        Mockito.verify(departamentoRepository).save(any());
    }
}