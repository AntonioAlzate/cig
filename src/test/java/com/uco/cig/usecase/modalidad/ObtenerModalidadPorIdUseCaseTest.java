package com.uco.cig.usecase.modalidad;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.modalidad.ports.ModalidadRepository;
import com.uco.cig.generate.ModalidadHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObtenerModalidadPorIdUseCaseTest {

    private static final String MODALIDAD_NO_ENCONTRADA = "La Modalidad de pago no ha sido encontrada";

    ModalidadRepository modalidadRepository;

    ObtenerModalidadPorIdUseCase obtenerModalidadPorIdUseCase;

    @BeforeEach
    public void setup(){
        modalidadRepository = mock(ModalidadRepository.class);
        obtenerModalidadPorIdUseCase = new ObtenerModalidadPorIdUseCase(modalidadRepository);
    }

    @Test
    void obtenerModalidadNotFoundTest(){
        Integer id = 1;

        when(modalidadRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () ->  obtenerModalidadPorIdUseCase.obtener(id),
                "Se esperaba excepción"
        );

        assertEquals(result.getMessage(), MODALIDAD_NO_ENCONTRADA + " id: " + id);
    }

    @Test
    void obtenerModalidadPorIdTest() throws BusinessException {
        Modalidad modalidad = ModalidadHelper.crearModalidad();

        when(modalidadRepository.findById(modalidad.getId())).thenReturn(Optional.of(modalidad));

        Modalidad result = obtenerModalidadPorIdUseCase.obtener(modalidad.getId());

        assertEquals(modalidad.getNombre(), result.getNombre());
        assertEquals(modalidad.getId(), result.getId());
    }

}