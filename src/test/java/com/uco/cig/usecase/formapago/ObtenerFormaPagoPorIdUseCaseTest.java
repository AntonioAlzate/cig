package com.uco.cig.usecase.formapago;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.formapago.ports.FormaPagoRepository;
import com.uco.cig.generate.FormaPagoHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ObtenerFormaPagoPorIdUseCaseTest {

    private static final String FORMA_PAGO_NO_ENCONTRADA = "La Forma de pago especificada no ha sido encontrada";

    FormaPagoRepository formaPagoRepository;

    ObtenerFormaPagoPorIdUseCase obtenerFormaPagoPorIdUseCase;

    @BeforeEach
    public void setup(){
        formaPagoRepository = mock(FormaPagoRepository.class);
        obtenerFormaPagoPorIdUseCase = new ObtenerFormaPagoPorIdUseCase(formaPagoRepository);
    }

    @Test
    public void obtenerFormaPagoNotFoundTest(){
        Integer id = 1;

        when(formaPagoRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> obtenerFormaPagoPorIdUseCase.obtener(id),
                "Se esperaba exepción"
        );

        assertTrue(result.getMessage().equals(FORMA_PAGO_NO_ENCONTRADA + " id: " + id));
    }

    @Test
    public void obtenerFormaPagoPorIdTest() throws BusinessException {
        FormaPago formaPago = FormaPagoHelper.crearFormaPago();

        when(formaPagoRepository.findById(formaPago.getId())).thenReturn(Optional.of(formaPago));

        FormaPago result = obtenerFormaPagoPorIdUseCase.obtener(formaPago.getId());

        assertEquals(formaPago.getId(), result.getId());
        assertEquals(formaPago.getNombre(), result.getNombre());
        assertEquals(formaPago.getValorMinimo(), result.getValorMinimo());
    }

}