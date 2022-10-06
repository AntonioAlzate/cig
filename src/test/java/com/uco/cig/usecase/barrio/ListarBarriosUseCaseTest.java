package com.uco.cig.usecase.barrio;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.generate.BarrioHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ListarBarriosUseCaseTest {

    BarrioRepository barrioRepository;

    ListarBarriosUseCase listarBarriosUseCase;

    @BeforeEach
    public void setup() {
        barrioRepository = mock(BarrioRepository.class);
        listarBarriosUseCase = new ListarBarriosUseCase(barrioRepository);
    }

    @Test
    void listarBarriosTest() throws BusinessException {
        List<Barrio> barrios = Arrays.asList(
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio(),
                BarrioHelper.construirAleratorio()
        );

        when(barrioRepository.findAll()).thenReturn(barrios);

        List<Barrio> result = listarBarriosUseCase.listar();

        assertEquals(4, result.size());
        assertEquals(barrios, result);

    }


}