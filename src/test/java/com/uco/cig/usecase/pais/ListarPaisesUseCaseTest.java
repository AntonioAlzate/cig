package com.uco.cig.usecase.pais;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.pais.ports.PaisRepository;
import com.uco.cig.generate.PaisHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarPaisesUseCaseTest {

    PaisRepository paisRepository;

    ListarPaisesUseCase listarPaisesUseCase;

    @BeforeEach
    public void setup(){
        paisRepository = mock(PaisRepository.class);
        listarPaisesUseCase = new ListarPaisesUseCase(paisRepository);
    }

    @Test
    void listarPaisesUseCaseTest() throws BusinessException {
        List<Pais> paises = Arrays.asList(
                PaisHelper.crearPais(),
                PaisHelper.crearPais(),
                PaisHelper.crearPais()
        );

        when(paisRepository.findAll()).thenReturn(paises);

        List<Pais> result = listarPaisesUseCase.listar();

        assertEquals(3, result.size());
        assertEquals( paises, result);
        Mockito.verify(paisRepository).findAll();
    }

}