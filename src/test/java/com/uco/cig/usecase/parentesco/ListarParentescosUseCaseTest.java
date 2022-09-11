package com.uco.cig.usecase.parentesco;

import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.parentesco.ports.ParentescoRespository;
import com.uco.cig.generate.ParentescoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarParentescosUseCaseTest {

    ParentescoRespository parentescoRespository;

    ListarParentescosUseCase listarParentescosUseCase;

    @BeforeEach
    public void setup(){
        parentescoRespository = mock(ParentescoRespository.class);
        listarParentescosUseCase = new ListarParentescosUseCase(parentescoRespository);
    }

    @Test
    public void listarParentescosUseCaseTest(){
        List<Parentesco> parentescos = Arrays.asList(
                ParentescoHelper.crearParentesco(),
                ParentescoHelper.crearParentesco(),
                ParentescoHelper.crearParentesco(),
                ParentescoHelper.crearParentesco(),
                ParentescoHelper.crearParentesco()
        );

        when(parentescoRespository.findAll()).thenReturn(parentescos);

        List<Parentesco> result = listarParentescosUseCase.listar();

        assertEquals(5, result.size());
        assertEquals(parentescos, result);
    }

}