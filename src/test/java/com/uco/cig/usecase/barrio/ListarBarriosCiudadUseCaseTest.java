package com.uco.cig.usecase.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.generate.BarrioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarBarriosCiudadUseCaseTest {

    BarrioRepository barrioRepository;

    ListarBarriosCiudadUseCase listarBarriosCiudadUseCase;

    @BeforeEach
    public void setup() {
        barrioRepository = mock(BarrioRepository.class);
        listarBarriosCiudadUseCase = new ListarBarriosCiudadUseCase(barrioRepository);
    }

    @Test
    public void listarBarriosCiudadTest() throws BusinessException {
        Integer Id = 1;

        List<Barrio> barrios = Arrays.asList(
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio()
        );

        when(barrioRepository.findAllByIdZAndIdZona_IdCiudad(Id)).thenReturn(barrios);

        List<Barrio> result = listarBarriosCiudadUseCase.listar(Id);

        assertEquals(4,result.size());
        assertEquals(barrios,result);
    }

}