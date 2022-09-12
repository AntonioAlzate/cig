package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavor;
import com.uco.cig.domain.detalle.cuentafavor.entrada.EntradaCuentaFavorRepository;
import com.uco.cig.generate.GeneralHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class RegistrarEntradaCuentaFavorUseCaseTest {

    EntradaCuentaFavorRepository entradaCuentaFavorRepository;

    RegistrarEntradaCuentaFavorUseCase registrarEntradaCuentaFavorUseCase;

    @BeforeEach
    public void setup(){
        entradaCuentaFavorRepository = mock(EntradaCuentaFavorRepository.class);
        registrarEntradaCuentaFavorUseCase = new RegistrarEntradaCuentaFavorUseCase(entradaCuentaFavorRepository);
    }

    @Test
    public void registrarEntradaCuentaFavorTest() throws BusinessException {
        String descripcion = UUID.randomUUID().toString();
        BigDecimal valor = GeneralHelper.obtenerValorBigDecimalAleatorio();
        DetalleCuentaFavor detalleCuentaFavor = DetalleCuentaFavor.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio()
        );
        EntradaCuentaFavor entradaCuentaFavor = EntradaCuentaFavor.nuevo(descripcion,valor,detalleCuentaFavor);

        when(entradaCuentaFavorRepository.save(any())).thenReturn(entradaCuentaFavor);

        EntradaCuentaFavor result = registrarEntradaCuentaFavorUseCase.registrar(descripcion,valor,detalleCuentaFavor);

        assertEquals(entradaCuentaFavor, result);
        assertEquals(entradaCuentaFavor.getId(),result.getId());
        assertEquals(entradaCuentaFavor.getDetalleCuentaFavor(),result.getDetalleCuentaFavor());
    }

}