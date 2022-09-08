package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ListarClientesUseCase {

    private final ClienteRepository clienteRepository;

    public ListarClientesUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }
}
