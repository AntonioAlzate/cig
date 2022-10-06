package com.uco.cig.usecase.pais;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.Pais;
import com.uco.cig.domain.pais.ports.PaisRepository;
import com.uco.cig.generate.PaisHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CrearPaisUseCaseTest {

    PaisRepository paisRepository;

    CrearPaisUseCase crearPaisUseCase;

    @BeforeEach
    public void setup(){
        paisRepository = mock(PaisRepository.class);
        crearPaisUseCase = new CrearPaisUseCase(paisRepository);
    }

    @Test
    void crearPaisTest() throws BusinessException {
        Pais pais = PaisHelper.crearPais();

        when(paisRepository.save(any())).thenReturn(pais);

        Pais result = crearPaisUseCase.crear(UUID.randomUUID().toString());

        assertEquals(pais.getNombre(), result.getNombre());
        assertEquals(pais.getId(), result.getId());
    }

}