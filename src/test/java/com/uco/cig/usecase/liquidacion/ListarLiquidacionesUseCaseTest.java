package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import com.uco.cig.generate.LiquidacionHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarLiquidacionesUseCaseTest {

    LiquidacionRepository liquidacionRepository;

    ListarLiquidacionesUseCase listarLiquidacionesUseCase;

    @BeforeEach
    public void setup(){
        liquidacionRepository = mock(LiquidacionRepository.class);
        listarLiquidacionesUseCase = new ListarLiquidacionesUseCase(liquidacionRepository);
    }

    @Test
    void listarLiquidacionesUseCaseTest() throws BusinessException {
        List<Liquidacion> liquidaciones = Arrays.asList(
                LiquidacionHelper.crearLiquidacion(),
                LiquidacionHelper.crearLiquidacion(),
                LiquidacionHelper.crearLiquidacion(),
                LiquidacionHelper.crearLiquidacion(),
                LiquidacionHelper.crearLiquidacion()
        );

        when(liquidacionRepository.findAll()).thenReturn(liquidaciones);

        List<Liquidacion> result = listarLiquidacionesUseCase.listar();

        assertEquals(5, result.size());
        assertEquals( liquidaciones, result);
        Mockito.verify(liquidacionRepository).findAll();
    }
}