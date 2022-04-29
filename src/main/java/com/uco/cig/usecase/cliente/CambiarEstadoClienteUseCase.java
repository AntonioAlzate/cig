package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.estado.EstadoEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CambiarEstadoClienteUseCase {

    private static final String ACTIVO = EstadoEnum.ACTIVO.name();
    private static final String INACTIVO = EstadoEnum.INACTIVO.name();
    private static final String CLIENTE_NO_ENCONTRADO = "El cliente al que desea actualizar el estado no existe.";

    private final ClienteRepository clienteRepository;

    public CambiarEstadoClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cambiarEstado(Integer idCliente){

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(cliente.isEmpty()){
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO);
        }

        String estadoActual = cliente.get().getEstado().getNombre();
        String estadoNuevo = estadoActual.equals(ACTIVO) ? INACTIVO : ACTIVO;

        return clienteRepository.cambiarEstado(idCliente, estadoNuevo);
    }
}
