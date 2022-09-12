package com.uco.cig.usecase.parentesco;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.parentesco.ports.ParentescoRespository;
import com.uco.cig.generate.ParentescoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ObtenerParentescoPorIdUseCaseTest {

    private static final String PARENTESCO_CON_ID_NO_ENCONTRADO = "El parentesco con id especificado no ha sido encontrado";

    ParentescoRespository parentescoRespository;

    ObtenerParentescoPorIdUseCase obtenerParentescoPorIdUseCase;

    @BeforeEach
    public void setup(){
        parentescoRespository = mock(ParentescoRespository.class);
        obtenerParentescoPorIdUseCase = new ObtenerParentescoPorIdUseCase(parentescoRespository);
    }

    @Test
    public void obtenerParentescoNotFound(){
        Integer id = 1;

        when(parentescoRespository.findById(id)).thenReturn(Optional.empty());

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> obtenerParentescoPorIdUseCase.obtener(id),
                "Se esperaba excepción"
        );

        assertTrue(result.getMessage().equals(PARENTESCO_CON_ID_NO_ENCONTRADO));
    }

    @Test
    public void obtenerParentescoPorIdTest(){
        Parentesco parentesco = ParentescoHelper.crearParentesco();

        when(parentescoRespository.findById(any())).thenReturn(Optional.of(parentesco));

        Parentesco result = obtenerParentescoPorIdUseCase.obtener(parentesco.getId());

        assertEquals(parentesco.getNombre(), result.getNombre());
        assertEquals(parentesco.getId(), result.getId());
    }

}