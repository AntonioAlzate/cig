package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuentacliente.CuentaCliente;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CambiarEstadoCuentaClienteUseCaseTest {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente al que desea actualizar el estado no existe.";

    ClienteRepository clienteRepository;
    CambiarEstadoCuentaClienteUseCase cambiarEstadoCuentaClienteUseCase;

    @BeforeEach
    void setup(){
        clienteRepository = mock(ClienteRepository.class);
        cambiarEstadoCuentaClienteUseCase = new CambiarEstadoCuentaClienteUseCase(clienteRepository);
    }

    @Test
    void cambiarEstadoCuentaClienteTest() throws BusinessException {
        String estadoInicial = "AL_DIA";
        String estadoFinal = "MORA";
        Cliente clienteInicial = ClienteHelper.crearNuevoCliente();
        Integer idCliente = clienteInicial.getId();

        Cliente clienteActualizado = Cliente.construir(
                clienteInicial.getId(),
                clienteInicial.getPersona(),
                clienteInicial.getCuentaCliente(),
                clienteInicial.getEstado()
        );
        clienteActualizado.setCuentaCliente(CuentaCliente.construir(
                clienteInicial.getCuentaCliente().getId(),
                clienteInicial.getCuentaCliente().getCupo(),
                clienteInicial.getCuentaCliente().getSaldoDeuda(),
                new EstadoCuentaCliente(clienteInicial.getCuentaCliente().getEstadoCuentaCliente().getId(), estadoFinal),
                clienteInicial.getCuentaCliente().getDetalleCuentaFavor()
        ));

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(clienteInicial));
        when(clienteRepository.cambiarEstadoCuenta(idCliente, estadoFinal)).thenReturn(clienteActualizado);

        Cliente clienteFinal = cambiarEstadoCuentaClienteUseCase.cambiarEstado(idCliente);

        Mockito.verify(clienteRepository).findById(idCliente);
        Mockito.verify(clienteRepository).cambiarEstadoCuenta(idCliente, estadoFinal);

        assertEquals(estadoFinal, clienteFinal.getCuentaCliente().getEstadoCuentaCliente().getEstado());
    }

    @Test
    void cambiarEstadoClienteNoExistenteTest() {
        Integer idCliente = 1;

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> cambiarEstadoCuentaClienteUseCase.cambiarEstado(idCliente),
                "se esperaba excepcion"
        );

        assertTrue(exception.getMessage().contains(CLIENTE_NO_ENCONTRADO));
    }

}