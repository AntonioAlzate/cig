package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ActualizarClienteUseCase {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente que intenta actualizar no se ha encontrado";
    private static final String BARRIO_REQUERIDO = "No es posible actualizar un cliente sin especificar un barrio valido";
    private static final String IDENTIFICACION_YA_REGISTRADA = "La identificación ingresada ya se encuentra registrada en otro usuario por lo tanto no se puede actualizar";

    private final ClienteRepository clienteRepository;
    private final BarrioRepository barrioRepository;
    private final PersonaRepository personaRepository;

    public ActualizarClienteUseCase(ClienteRepository clienteRepository, BarrioRepository barrioRepository, PersonaRepository personaRepository) {
        this.clienteRepository = clienteRepository;
        this.barrioRepository = barrioRepository;
        this.personaRepository = personaRepository;
    }

    public Cliente actualizar(ClienteCreacionDto creacionDto, Integer id) throws BusinessException {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty())
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO);

        cliente = pasarDatosDTO(cliente.get(), creacionDto);

        if(yaExistePersona(cliente.get().getPersona().getIdentificacion()))
            throw new BusinessException(IDENTIFICACION_YA_REGISTRADA);

        return clienteRepository.save(cliente.get());
    }

    private Optional<Cliente> pasarDatosDTO(Cliente cliente, ClienteCreacionDto creacionDto) throws BusinessException {

        Barrio barrio = barrioRepository.findById(creacionDto.getIdBarrio()).orElse(null);

        if(barrio == null)
            throw new BusinessException(BARRIO_REQUERIDO);

        cliente.getPersona().setIdentificacion(creacionDto.getIdentificacion().trim());
        cliente.getPersona().setPrimerNombre(creacionDto.getPrimerNombre().trim().toUpperCase());
        cliente.getPersona().setSegundoNombre(creacionDto.getSegundoNombre().trim().toUpperCase());
        cliente.getPersona().setPrimerApellido(creacionDto.getPrimerApellido().trim().toUpperCase());
        cliente.getPersona().setSegundoApellido(creacionDto.getSegundoApellido().trim().toUpperCase());
        cliente.getPersona().setDireccion(creacionDto.getDireccion().trim().toUpperCase());
        cliente.getPersona().setTelefono(creacionDto.getTelefono().trim());
        cliente.getCuentaCliente().setCupo(new BigDecimal(creacionDto.getCupo()));
        cliente.getPersona().setBarrio(barrio);

        Cliente clienteValidado =
                Cliente.construir(
                        cliente.getId(),
                        cliente.getPersona(),
                        cliente.getCuentaCliente(),
                        cliente.getEstado()
                );

        return Optional.of(clienteValidado);
    }

    private boolean yaExistePersona(String identificacion) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacion);

        return persona.isPresent();
    }
}
