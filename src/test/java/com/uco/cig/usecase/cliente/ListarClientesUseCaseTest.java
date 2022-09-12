package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarClientesUseCaseTest {

    ClienteRepository clienteRepository;

    ListarClientesUseCase listarClientesUseCase;

    @BeforeEach
    public void setup(){
        clienteRepository = mock(ClienteRepository.class);
        listarClientesUseCase = new ListarClientesUseCase(clienteRepository);
    }

    @Test
    public void listarClientesUseCaseTest() throws BusinessException {
        List<Cliente> clientes = Arrays.asList(
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente()
        );

        when(clienteRepository.findAll()).thenReturn(clientes);

        List<Cliente> result = listarClientesUseCase.listar();

        assertEquals(3, result.size());
        assertEquals(clientes, result);
    }
}