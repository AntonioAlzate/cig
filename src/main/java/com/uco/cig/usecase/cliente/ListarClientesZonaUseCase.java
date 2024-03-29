package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ListarClientesZonaUseCase {

    private final ClienteRepository clienteRepository;

    public ListarClientesZonaUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar(Integer idZona){
        return clienteRepository.findClientesConIdZona(idZona);
    }
}
