package com.uco.cig.usecase.despacho;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.despacho.registro.RegistroDespacho;
import com.uco.cig.domain.despacho.registro.ports.RegistroDespachoRepository;
import com.uco.cig.generate.TrabajadorHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObtenerRegistrosConDetallesUseCaseTest {

    RegistroDespachoRepository registroDespachoRepository;

    ObtenerRegistrosConDetallesUseCase obtenerRegistrosConDetallesUseCase;

    @BeforeEach
    public void setup(){
        registroDespachoRepository = mock(RegistroDespachoRepository.class);
        obtenerRegistrosConDetallesUseCase = new ObtenerRegistrosConDetallesUseCase(registroDespachoRepository);
    }

    @Test
    void obtenerRegistrosConDetallesTest() throws BusinessException {
        List<RegistroDespacho> registroDespachos = Arrays.asList(
                RegistroDespacho.nuevo(
                        TrabajadorHelper.crearTrabajador(),
                        TrabajadorHelper.crearTrabajador(),
                        OffsetDateTime.now()
                        ),
                RegistroDespacho.nuevo(
                        TrabajadorHelper.crearTrabajador(),
                        TrabajadorHelper.crearTrabajador(),
                        OffsetDateTime.now()
                        ),
                RegistroDespacho.nuevo(
                        TrabajadorHelper.crearTrabajador(),
                        TrabajadorHelper.crearTrabajador(),
                        OffsetDateTime.now()
                        )

        );

        when(registroDespachoRepository.findAll()).thenReturn(registroDespachos);

        List<RegistroDespacho> result = obtenerRegistrosConDetallesUseCase.registrosConDetalles();

        assertEquals(3, result.size());
        assertEquals(registroDespachos, result);
    }

}