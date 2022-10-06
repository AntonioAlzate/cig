package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ActualizarCupoDeudaAbonoCuentaClienteUseCaseTest {

    ClienteRepository clienteRepository;
    RegistrarEntradaCuentaFavorUseCase entradaCuentaFavorUseCase;
    ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaAbonoCuentaClienteUseCase;

    @BeforeEach
    public void setup() {
        clienteRepository = mock(ClienteRepository.class);
        entradaCuentaFavorUseCase = mock(RegistrarEntradaCuentaFavorUseCase.class);
        actualizarCupoDeudaAbonoCuentaClienteUseCase = new ActualizarCupoDeudaAbonoCuentaClienteUseCase(clienteRepository, entradaCuentaFavorUseCase);
    }

    @Test
    void abonarvalorMayorOIgualDeudaTest() throws BusinessException {
        BigDecimal valorAbono = new BigDecimal(50000);
        BigDecimal cupo = new BigDecimal(150000);
        BigDecimal saldoDeuda = new BigDecimal(50000);

        CuentaCliente cuentaClienteInicial = ClienteHelper.crearNuevaCuentaCliente(cupo, saldoDeuda);
        Cliente clienteInicial = ClienteHelper.crearNuevoCliente(cuentaClienteInicial);

        CuentaCliente cuentaClienteFinal = ClienteHelper.crearNuevaCuentaCliente(cupo, saldoDeuda);
        Cliente clienteFinal = ClienteHelper.crearNuevoCliente(cuentaClienteFinal);
        clienteFinal.getCuentaCliente().setCupo(cupo.add(saldoDeuda));
        clienteFinal.getCuentaCliente().setSaldoDeuda(BigDecimal.ZERO);

        when(entradaCuentaFavorUseCase.registrar(any(), any(), any())).thenReturn(null);
        when(clienteRepository.save(clienteInicial)).thenReturn(clienteFinal);

        Cliente clienteActualizado = actualizarCupoDeudaAbonoCuentaClienteUseCase.actualizar(valorAbono, clienteInicial);

        assertEquals(BigDecimal.ZERO, clienteActualizado.getCuentaCliente().getSaldoDeuda());
    }

    @Test
    void abonarvalorMenorDeudaTest() throws BusinessException {
        BigDecimal valorAbono = new BigDecimal(20000);
        BigDecimal cupo = new BigDecimal(150000);
        BigDecimal saldoDeuda = new BigDecimal(50000);

        CuentaCliente cuentaClienteInicial = ClienteHelper.crearNuevaCuentaCliente(cupo, saldoDeuda);
        Cliente clienteInicial = ClienteHelper.crearNuevoCliente(cuentaClienteInicial);

        CuentaCliente cuentaClienteFinal = ClienteHelper.crearNuevaCuentaCliente(cupo, saldoDeuda);
        Cliente clienteFinal = ClienteHelper.crearNuevoCliente(cuentaClienteFinal);
        clienteFinal.getCuentaCliente().setCupo(cupo.add(saldoDeuda));
        clienteFinal.getCuentaCliente().setSaldoDeuda(saldoDeuda.subtract(valorAbono));

        when(entradaCuentaFavorUseCase.registrar(any(), any(), any())).thenReturn(null);
        when(clienteRepository.save(clienteInicial)).thenReturn(clienteFinal);

        Cliente clienteActualizado = actualizarCupoDeudaAbonoCuentaClienteUseCase.actualizar(valorAbono, clienteInicial);

        assertEquals(new BigDecimal(30000), clienteActualizado.getCuentaCliente().getSaldoDeuda());
    }

}