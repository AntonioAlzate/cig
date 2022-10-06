package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.generate.ClienteHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ObtenerClientePorIdentificacionUseCaseTest {

    ClienteRepository clienteRepository;

    ObtenerClientePorIdentificacionUseCase obtenerClientePorIdentificacionUseCase;

    @BeforeEach
    public void setup(){
        clienteRepository = mock(ClienteRepository.class);
        obtenerClientePorIdentificacionUseCase = new ObtenerClientePorIdentificacionUseCase(clienteRepository);
    }

    @Test
    void obtenerClientePorIdentificacionUseCaseTest() throws BusinessException {
        Cliente cliente = ClienteHelper.crearNuevoCliente();

        when(clienteRepository.findByIdentificacion(cliente.getPersona().getIdentificacion())).thenReturn(cliente);

        Cliente result = obtenerClientePorIdentificacionUseCase.obtener(cliente.getPersona().getIdentificacion());

        assertEquals(cliente.getId(), result.getId());
        assertEquals(cliente.getPersona(), result.getPersona());
        assertEquals(cliente, result);
    }

}