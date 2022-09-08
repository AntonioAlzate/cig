package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.shared.dtos.ClienteConReferenciasDTO;
import com.uco.cig.usecase.referencia.ListarReferenciasDeClienteUseCase;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListarClientesConReferenciaUseCase {

    private final ListarClientesUseCase listarClientesUseCase;
    private final ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase;

    public ListarClientesConReferenciaUseCase(ListarClientesUseCase listarClientesUseCase, ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase) {
        this.listarClientesUseCase = listarClientesUseCase;
        this.listarReferenciasDeClienteUseCase = listarReferenciasDeClienteUseCase;
    }

    public List<ClienteConReferenciasDTO> listar() {
        List<ClienteConReferenciasDTO> clienteConReferencias = new ArrayList<>();
        List<Cliente> clientes = listarClientesUseCase.listar();

        for (Cliente cliente : clientes) {
            List<Referencia> referencias = listarReferenciasDeClienteUseCase.listar(cliente.getId());

            clienteConReferencias.add(new ClienteConReferenciasDTO(cliente, referencias));
        }

        return clienteConReferencias;
    }
}
