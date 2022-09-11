package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarClientesZonaUseCaseTest {

    ClienteRepository clienteRepository;

    ListarClientesZonaUseCase listarClientesZonaUseCase;

    @BeforeEach
    public void setup() {
        clienteRepository = mock(ClienteRepository.class);
        listarClientesZonaUseCase = new ListarClientesZonaUseCase(clienteRepository);
    }

    @Test
    public void listarClientesZonaUseCaseTest() throws BusinessException {
        Integer id = 1;

        List<Cliente> clientes = Arrays.asList(
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente()
        );

        when(clienteRepository.findClientesConIdZona(id)).thenReturn(clientes);

        List<Cliente> result= listarClientesZonaUseCase.listar(id);

        assertEquals(4, result.size());
        assertEquals(clientes, result);
    }
}