package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerClientePorIdentificacionUseCase {

    private final ClienteRepository clienteRepository;

    public ObtenerClientePorIdentificacionUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente obtener(String identificacion){
        return clienteRepository.findByIdentificacion(identificacion);
    }
}
