package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaCliente;
import com.uco.cig.domain.estado.cuentacliente.EstadoCuentaClienteEnum;
import com.uco.cig.domain.estado.cuentacliente.ports.EstadoCuentaClienteRepository;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.shared.dtos.ReferenciaCreacionDTO;
import com.uco.cig.usecase.referencia.CrearReferenciaUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CrearClienteUseCase {

    private static final String BARRIO_NO_ENCONTRADO = "El Barrio especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String ESTADO_CUENTA_NO_ENCONTRADO = "El Estado Cuenta especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String CLIENTE_YA_REGISTRADO = "El cliente que intenta registrar ya se encuentra en el sistema";

    private final ClienteRepository clienteRepository;
    private final BarrioRepository barrioRepository;
    private final EstadoRepository estadoRepository;
    private final EstadoCuentaClienteRepository estadoCuentaClienteRepository;
    private final PersonaRepository personaRepository;
    private final CrearReferenciaUseCase crearReferenciaUseCase;

    public CrearClienteUseCase(ClienteRepository clienteRepository, BarrioRepository barrioRepository, EstadoRepository estadoRepository,
                               EstadoCuentaClienteRepository estadoCuentaClienteRepository, PersonaRepository personaRepository, CrearReferenciaUseCase crearReferenciaUseCase) {
        this.clienteRepository = clienteRepository;
        this.barrioRepository = barrioRepository;
        this.estadoRepository = estadoRepository;
        this.estadoCuentaClienteRepository = estadoCuentaClienteRepository;
        this.personaRepository = personaRepository;
        this.crearReferenciaUseCase = crearReferenciaUseCase;
    }

    public Cliente crear(ClienteCreacionDto creacionDto) throws BusinessException {

        if(yaExisteCliente(creacionDto.getIdentificacion().trim()))
            throw new BusinessException(CLIENTE_YA_REGISTRADO);

        Optional<Barrio> barrio =
                Optional.ofNullable(barrioRepository.findById(creacionDto.getIdBarrio()).orElseThrow(() -> new NotFoundException(BARRIO_NO_ENCONTRADO)));

        Optional<Estado> estado =
                Optional.ofNullable(estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString()).orElseThrow(() -> new NotFoundException(ESTADO_NO_ENCONTRADO)));

        Optional<EstadoCuentaCliente> estadoCuentaCliente =
                Optional.ofNullable(estadoCuentaClienteRepository.findByEstado(EstadoCuentaClienteEnum.AL_DIA.toString()).orElseThrow(() -> new NotFoundException(ESTADO_CUENTA_NO_ENCONTRADO)));

        BigDecimal cupo = new BigDecimal(creacionDto.getCupo());

        Cliente cliente = Cliente.nuevo(
                creacionDto.getIdentificacion().trim(),
                creacionDto.getPrimerNombre().trim().toUpperCase(),
                creacionDto.getSegundoNombre().trim().toUpperCase(),
                creacionDto.getPrimerApellido().trim().toUpperCase(),
                creacionDto.getSegundoApellido().trim().toUpperCase(),
                creacionDto.getDireccion().trim().toUpperCase(),
                creacionDto.getTelefono().trim(),
                barrio.orElse(null),
                cupo,
                estadoCuentaCliente.orElse(null),
                estado.orElse(null)
        );


        ReferenciaCreacionDTO referenciaCreacionDTO1 = new ReferenciaCreacionDTO(
                creacionDto.getNombreReferencia1().trim().toUpperCase(),
                creacionDto.getTelefonoReferencia1().trim(),
                creacionDto.getIdParentescoReferencia1()
        );

        ReferenciaCreacionDTO referenciaCreacionDTO2 = new ReferenciaCreacionDTO(
                creacionDto.getNombreReferencia2().trim().toUpperCase(),
                creacionDto.getTelefonoReferencia2().trim(),
                creacionDto.getIdParentescoReferencia2()
        );

        cliente = clienteRepository.save(cliente);
        crearReferenciaUseCase.crear(referenciaCreacionDTO1, cliente.getId());
        crearReferenciaUseCase.crear(referenciaCreacionDTO2, cliente.getId());

        return cliente;
    }

    private boolean yaExisteCliente(String identificacion) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacion);

        if(persona.isEmpty()){
            return false;
        }

        return clienteRepository.existsByIdPersona(persona.orElse(null));
    }


}
