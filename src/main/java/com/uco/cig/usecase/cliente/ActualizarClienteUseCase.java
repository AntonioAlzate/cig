package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.parentesco.Parentesco;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.domain.referencia.Referencia;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.usecase.parentesco.ObtenerParentescoPorIdUseCase;
import com.uco.cig.usecase.referencia.ListarReferenciasDeClienteUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ActualizarClienteUseCase {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente que intenta actualizar no se ha encontrado";
    private static final String BARRIO_REQUERIDO = "No es posible actualizar un cliente sin especificar un barrio valido";
    private static final String IDENTIFICACION_YA_REGISTRADA = "La identificación ingresada ya se encuentra registrada en otro usuario por lo tanto no se puede actualizar";

    private final ClienteRepository clienteRepository;
    private final BarrioRepository barrioRepository;
    private final PersonaRepository personaRepository;
    private final ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase;
    private final ObtenerParentescoPorIdUseCase obtenerParentescoPorIdUseCase;

    public ActualizarClienteUseCase(ClienteRepository clienteRepository, BarrioRepository barrioRepository, PersonaRepository personaRepository, ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase, ObtenerParentescoPorIdUseCase obtenerParentescoPorIdUseCase) {
        this.clienteRepository = clienteRepository;
        this.barrioRepository = barrioRepository;
        this.personaRepository = personaRepository;
        this.listarReferenciasDeClienteUseCase = listarReferenciasDeClienteUseCase;
        this.obtenerParentescoPorIdUseCase = obtenerParentescoPorIdUseCase;
    }

    public Cliente actualizar(ClienteCreacionDto creacionDto, Integer id) throws BusinessException {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if(cliente.isEmpty())
            throw new NotFoundException(CLIENTE_NO_ENCONTRADO);

        cliente = pasarDatosDTO(cliente.get(), creacionDto);

        if(yaExistePersona(cliente.get().getPersona().getIdentificacion(), creacionDto.getIdentificacion()))
            throw new BusinessException(IDENTIFICACION_YA_REGISTRADA);

        List<Referencia> referencias = listarReferenciasDeClienteUseCase.listar(id);
        Referencia referencia1 = referencias.get(0);
        Referencia referencia2 = referencias.get(1);

        referencia1 = actualizarReferenciaDTO(referencia1, creacionDto.getNombreReferencia1(), creacionDto.getTelefonoReferencia1(), creacionDto.getIdParentescoReferencia1());
        referencia2 = actualizarReferenciaDTO(referencia2, creacionDto.getNombreReferencia2(), creacionDto.getTelefonoReferencia2(), creacionDto.getIdParentescoReferencia2());

        return clienteRepository.update(cliente.get(), referencia1, referencia2);
    }

    private Referencia actualizarReferenciaDTO(Referencia referencia, String nombreReferencia, String telefonoReferencia, Integer idParentescoReferencia) throws BusinessException {
        Parentesco parentesco = obtenerParentescoPorIdUseCase.obtener(idParentescoReferencia);

        referencia.setNombre(nombreReferencia.trim().toUpperCase());
        referencia.setTelefono(telefonoReferencia.trim());
        referencia.setParentesco(parentesco);
        return referencia;
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

    private boolean yaExistePersona(String identificacionCliente, String identificacionActualizar) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacionActualizar);

        if(persona.isEmpty())
            return false;

        if(persona.get().getIdentificacion().equals(identificacionCliente))
            return false;

        return true;
    }
}
