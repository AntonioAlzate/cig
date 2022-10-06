package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaClienteEnum;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CambiarEstadoCuentaClienteUseCase {

    private static final String AL_DIA = EstadoCuentaClienteEnum.AL_DIA.name();
    private static final String MORA = EstadoCuentaClienteEnum.MORA.name();
    private static final String CLIENTE_NO_ENCONTRADO = "El cliente al que desea actualizar el estado no existe.";

    private final ClienteRepository clienteRepository;

    public CambiarEstadoCuentaClienteUseCase(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente cambiarEstado(Integer idCliente){

        Optional<Cliente> cliente = clienteRepository.findById(idCliente);

        if(cliente.isEmpty()){
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO);
        }

        String estadoActual = cliente.get().getCuentaCliente().getEstadoCuentaCliente().getEstado();
        String estadoNuevo = estadoActual.equals(AL_DIA) ? MORA : AL_DIA;

        return clienteRepository.cambiarEstadoCuenta(idCliente, estadoNuevo);
    }
}
