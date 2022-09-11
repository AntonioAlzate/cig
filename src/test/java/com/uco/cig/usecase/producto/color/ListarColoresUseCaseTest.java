package com.uco.cig.usecase.producto.color;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.color.Color;
import com.uco.cig.domain.color.ports.ColorRepository;
import com.uco.cig.generate.ColorHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarColoresUseCaseTest {

    ColorRepository colorRepository;

    ListarColoresUseCase listarColoresUseCase;

    @BeforeEach
    public void setup(){
        colorRepository = mock(ColorRepository.class);
        listarColoresUseCase = new ListarColoresUseCase(colorRepository);
    }

    @Test
    public void listarColoresUseCaseTest() throws BusinessException {
        List<Color> colores = Arrays.asList(
                ColorHelper.crearColor(),
                ColorHelper.crearColor(),
                ColorHelper.crearColor()
        );

        when(colorRepository.findAll()).thenReturn(colores);

        List<Color> result = listarColoresUseCase.listar();

        assertEquals(3, result.size());
        assertEquals(colores, result);
        Mockito.verify(colorRepository).findAll();
    }

}