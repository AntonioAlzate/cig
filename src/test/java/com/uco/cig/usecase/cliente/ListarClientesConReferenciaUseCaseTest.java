package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.generate.ClienteHelper;
import com.uco.cig.generate.ReferenciasHelper;
import com.uco.cig.shared.dtos.ClienteConReferenciasDTO;
import com.uco.cig.usecase.referencia.ListarReferenciasDeClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

class ListarClientesConReferenciaUseCaseTest {

    ListarClientesUseCase listarClientesUseCase;

    ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase;

    ListarClientesConReferenciaUseCase listarClientesConReferenciaUseCase;

    @BeforeEach
    public void setup() {
        listarClientesUseCase = mock(ListarClientesUseCase.class);
        listarReferenciasDeClienteUseCase = mock(ListarReferenciasDeClienteUseCase.class);
        listarClientesConReferenciaUseCase = new ListarClientesConReferenciaUseCase(listarClientesUseCase, listarReferenciasDeClienteUseCase);
    }

    @Test
    public void ListarClientesConReferenciaUsecaseTest() throws BusinessException {
        List<Cliente> clientes = Arrays.asList(
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente(),
                ClienteHelper.crearNuevoCliente()
        );

        List<Referencia> referencias = Arrays.asList(
                ReferenciasHelper.crearReferencia(clientes.get(0)),
                ReferenciasHelper.crearReferencia(clientes.get(1)),
                ReferenciasHelper.crearReferencia(clientes.get(2)),
                ReferenciasHelper.crearReferencia(clientes.get(3))
        );


        List<ClienteConReferenciasDTO> clienteConReferencias;
        when(listarClientesUseCase.listar()).thenReturn(clientes);

        for (int i = 0; i < clientes.size(); i++){
            when(listarReferenciasDeClienteUseCase.listar(clientes.get(i).getId())).thenReturn(Arrays.asList(referencias.get(i)));
        }

        clienteConReferencias = listarClientesConReferenciaUseCase.listar();

        assertEquals(4,clienteConReferencias.size());
        assertEquals(1,clienteConReferencias.get(0).getReferencias().size());
    }
}