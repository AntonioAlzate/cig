package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.estado.cuentacliente.ports.EstadoCuentaClienteRepository;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.generate.PersonaHelper;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.usecase.referencia.CrearReferenciaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


class CrearClienteUseCaseTest {

    private static final String BARRIO_NO_ENCONTRADO = "El Barrio especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String ESTADO_CUENTA_NO_ENCONTRADO = "El Estado Cuenta especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String CLIENTE_YA_REGISTRADO = "El cliente que intenta registrar ya se encuentra en el sistema";

    ClienteRepository clienteRepository;
    BarrioRepository barrioRepository;
    EstadoRepository estadoRepository;
    EstadoCuentaClienteRepository estadoCuentaClienteRepository;
    PersonaRepository personaRepository;
    CrearReferenciaUseCase crearReferenciaUseCase;

    CrearClienteUseCase crearClienteUseCase;

    @BeforeEach
    void setup() {
        clienteRepository = mock(ClienteRepository.class);
        barrioRepository = mock(BarrioRepository.class);
        estadoRepository = mock(EstadoRepository.class);
        estadoCuentaClienteRepository = mock(EstadoCuentaClienteRepository.class);
        personaRepository = mock(PersonaRepository.class);
        crearReferenciaUseCase = mock(CrearReferenciaUseCase.class);
        crearClienteUseCase = new CrearClienteUseCase(clienteRepository, barrioRepository, estadoRepository, estadoCuentaClienteRepository, personaRepository, crearReferenciaUseCase);
    }

    @Test
    void personaYaExistenteTest() throws BusinessException {
        ClienteCreacionDto clienteCreacionDto = getCreacionDTO();
        String identificacion = clienteCreacionDto.getIdentificacion();
        Persona persona = PersonaHelper.crearNueva(identificacion);

        when(personaRepository.findByIdentificacion(identificacion)).thenReturn(Optional.of(persona));
        when(clienteRepository.existsByIdPersona(persona)).thenReturn(true);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> crearClienteUseCase.crear(clienteCreacionDto),
                "se esperaba excepcion"
        );

        assertTrue(exception.getMessage().contains(CLIENTE_YA_REGISTRADO));
    }

    private ClienteCreacionDto getCreacionDTO() {
        return new ClienteCreacionDto(
                "1234567890", "juan", "pablo", "martinez",
                "perez", "esquina 5", "1234567890", "100000", 1,
                null, null, null,null,null,null
        );
    }

}