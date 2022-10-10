package com.uco.cig.usecase.producto.dimesion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.dimension.Dimension;
import com.uco.cig.domain.dimension.ports.DimensionRepository;
import com.uco.cig.generate.DimensionesHelper;
import com.uco.cig.generate.GeneralHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarDimensionesCategoriaUseCaseTest {

    DimensionRepository dimensionRepository;

    ListarDimensionesCategoriaUseCase listarDimensionesCategoriaUseCase;

    @BeforeEach
    public void setup(){
        dimensionRepository = mock(DimensionRepository.class);
        listarDimensionesCategoriaUseCase = new ListarDimensionesCategoriaUseCase(dimensionRepository);
    }

    @Test
    void listarDimensionesUsecaseTest() throws BusinessException {
        Integer idCategoria = GeneralHelper.obtenerEnteroAleatorio();

        List<Dimension> dimensiones = Arrays.asList(
                DimensionesHelper.crearDiemension(),
                DimensionesHelper.crearDiemension(),
                DimensionesHelper.crearDiemension(),
                DimensionesHelper.crearDiemension(),
                DimensionesHelper.crearDiemension()
        );

        when(dimensionRepository.findAllByCategoria(idCategoria)).thenReturn(dimensiones);

        List<Dimension> result = listarDimensionesCategoriaUseCase.listar(idCategoria);

        assertEquals(5, result.size());
        assertEquals(dimensiones, result);
    }

}