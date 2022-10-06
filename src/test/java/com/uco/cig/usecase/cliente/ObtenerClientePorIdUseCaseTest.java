package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObtenerClientePorIdUseCaseTest {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente buscado no ha sido encontrado";

    ClienteRepository clienteRepository;

    ObtenerClientePorIdUseCase obtenerClientePorIdUseCase;

    @BeforeEach
    public void setup(){
        clienteRepository = mock(ClienteRepository.class);
        obtenerClientePorIdUseCase = new ObtenerClientePorIdUseCase(clienteRepository);
    }

    @Test
    void obtenerClientePorIdInexistenteTest(){
        Integer id = 1;

        when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> obtenerClientePorIdUseCase.obtener(id),
                    "Se esperaba excepción"
        );

        assertTrue(result.getMessage().contains(CLIENTE_NO_ENCONTRADO));
    }

    @Test
    void obtenerClientePorIdUseCaseTest() throws BusinessException {
        Optional<Cliente> cliente = Optional.of(ClienteHelper.crearNuevoCliente());

        when(clienteRepository.findById(cliente.get().getId())).thenReturn(cliente);

        Cliente result = obtenerClientePorIdUseCase.obtener(cliente.get().getId());

        assertEquals(cliente.get().getPersona(), result.getPersona());
        assertEquals(cliente.get().getCuentaCliente(), result.getCuentaCliente());
    }
}