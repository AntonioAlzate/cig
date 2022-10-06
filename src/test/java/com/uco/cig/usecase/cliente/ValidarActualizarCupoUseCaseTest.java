package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.cuentacliente.ports.CuentaClienteRepository;
import com.uco.cig.generate.CuentaClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class ValidarActualizarCupoUseCaseTest {

    private static final String CUPO_INSUFICIENTE = "El cupo del cliente es menor al valor de la venta";

    CuentaClienteRepository cuentaClienteRepository;

    ValidarActualizarCupoUseCase validarActualizarCupoUseCase;

    @BeforeEach
    public void setup(){
        cuentaClienteRepository = mock(CuentaClienteRepository.class);
        validarActualizarCupoUseCase = new ValidarActualizarCupoUseCase(cuentaClienteRepository);
    }

    @Test
    void validarActualizarBadRequest() throws BusinessException {
        CuentaCliente cuentaCliente = CuentaClienteHelper.crearCuentaClienteSinCupo();
        BigDecimal cuotaInicial = BigDecimal.ONE;
        BigDecimal valorTotalCompra = BigDecimal.TEN;

        BadRequestException result = assertThrows(
                BadRequestException.class,
                () -> validarActualizarCupoUseCase.validarActualizar(cuentaCliente,valorTotalCompra,cuotaInicial),
                "se esperaba exepción"
        );

        assertTrue(result.getMessage().contains(CUPO_INSUFICIENTE));
    }

    @Test
    void validarActualizarCupoTest() throws BusinessException {
        CuentaCliente cuentaCliente = CuentaClienteHelper.crearCuentaClienteVeinteCupo();
        BigDecimal cuotaInicial = BigDecimal.ONE;
        BigDecimal valorTotalCompra = BigDecimal.TEN;

        BigDecimal valorCredito = valorTotalCompra.subtract(cuotaInicial);
        BigDecimal nuevaDeuda = cuentaCliente.getSaldoDeuda().add(valorCredito);
        BigDecimal nuevoCupo = cuentaCliente.getCupo().subtract(valorCredito);

        CuentaCliente cuentaCliente1 = cuentaCliente;
        cuentaCliente.setCupo(nuevoCupo);
        cuentaCliente.setSaldoDeuda(nuevaDeuda);

        when(cuentaClienteRepository.save(any())).thenReturn(cuentaCliente1);

        validarActualizarCupoUseCase.validarActualizar(cuentaCliente,valorTotalCompra,cuotaInicial);

        Mockito.verify(cuentaClienteRepository).save(cuentaCliente1);
        //CuentaCliente result = validarActualizarCupoUseCase.validarActualizar(cuentaCliente,valorTotalCompra,cuotaInicial);
    }
}