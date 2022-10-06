package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CambiarEstadoClienteUseCaseTest {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente al que desea actualizar el estado no existe.";

    ClienteRepository clienteRepository;
    CambiarEstadoClienteUseCase cambiarEstadoClienteUseCase;

    @BeforeEach
    public void setup(){
        clienteRepository = mock(ClienteRepository.class);
        cambiarEstadoClienteUseCase = new CambiarEstadoClienteUseCase(clienteRepository);
    }

    @Test
    void cambiarEstadoClienteTest() throws BusinessException {
        String estadoInicial = "ACTIVO";
        Cliente cliente = ClienteHelper.crearNuevoCliente(estadoInicial);
        Integer idCliente = cliente.getId();

        Cliente clienteActualizado = new Cliente();
        clienteActualizado = cliente;
        String estadoFinal = "INACTIVO";
        clienteActualizado.setEstado(new Estado(1, estadoFinal));

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(cliente));
        when(clienteRepository.cambiarEstado(idCliente, estadoInicial)).thenReturn(clienteActualizado);

        Cliente clienteFinal = cambiarEstadoClienteUseCase.cambiarEstado(idCliente);

        assertEquals("INACTIVO", clienteFinal.getEstado().getNombre());

        Mockito.verify(clienteRepository).findById(idCliente);
        Mockito.verify(clienteRepository).cambiarEstado(idCliente, estadoInicial);
    }

    @Test
    void cambiarEstadoClienteNoExistenteTest(){
        Integer id = 1;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> cambiarEstadoClienteUseCase.cambiarEstado(id),
                "Se esperaba excepción"
        );

        assertTrue(exception.getMessage().contains(CLIENTE_NO_ENCONTRADO));

    }
}