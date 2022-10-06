package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObtenerClientePorIdUseCase {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente buscado no ha sido encontrado";

    private final ClienteRepository clienteRepository;

    public ObtenerClientePorIdUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente obtener(Integer idCliente){
        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(cliente.isEmpty())
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO + " id: " + idCliente);

        return cliente.get();
    }
}
