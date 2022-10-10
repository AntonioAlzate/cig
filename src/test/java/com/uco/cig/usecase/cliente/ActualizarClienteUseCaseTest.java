package com.uco.cig.usecase.cliente;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.generate.BarrioHelper;
import com.uco.cig.generate.ClienteHelper;
import com.uco.cig.generate.GeneralHelper;
import com.uco.cig.generate.PersonaHelper;
import com.uco.cig.shared.dtos.ClienteCreacionDto;
import com.uco.cig.usecase.parentesco.ObtenerParentescoPorIdUseCase;
import com.uco.cig.usecase.referencia.ListarReferenciasDeClienteUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ActualizarClienteUseCaseTest {

    private static final String CLIENTE_NO_ENCONTRADO = "El cliente que intenta actualizar no se ha encontrado";
    private static final String IDENTIFICACION_YA_REGISTRADA = "La identificación ingresada ya se encuentra registrada en otro usuario por lo tanto no se puede actualizar";

    ClienteRepository clienteRepository;
    BarrioRepository barrioRepository;
    PersonaRepository personaRepository;
    ListarReferenciasDeClienteUseCase listarReferenciasDeClienteUseCase;

    ActualizarClienteUseCase actualizarClienteUseCase;
    ObtenerParentescoPorIdUseCase obtenerParentescoPorIdUseCase;

    @BeforeEach
    private void setup() {
        clienteRepository = mock(ClienteRepository.class);
        barrioRepository = mock(BarrioRepository.class);
        personaRepository = mock(PersonaRepository.class);
        listarReferenciasDeClienteUseCase = mock(ListarReferenciasDeClienteUseCase.class);
        obtenerParentescoPorIdUseCase = mock(ObtenerParentescoPorIdUseCase.class);
        actualizarClienteUseCase = new ActualizarClienteUseCase(clienteRepository, barrioRepository, personaRepository, listarReferenciasDeClienteUseCase, obtenerParentescoPorIdUseCase);
    }

    @Test
    void actualizarClienteUseCaseTest() throws BusinessException {
        Integer id = 1;
        Integer idBarrio = 1;
        String identificacion = "1234567890";
        Optional<Cliente> cliente = Optional.of(ClienteHelper.crearNuevoCliente());
        Optional<Barrio> barrio = Optional.of(BarrioHelper.construirAleratorio());

        when(clienteRepository.findById(id)).thenReturn(cliente);
        when(personaRepository.findByIdentificacion(identificacion)).thenReturn(null);
        when(barrioRepository.findById(idBarrio)).thenReturn(barrio);
        when(clienteRepository.save(any())).thenReturn(cliente.get());

        Cliente clienteCreado = actualizarClienteUseCase.actualizar(getCreacionDTO(identificacion, idBarrio),id);

        assertEquals(cliente.get(), clienteCreado);
    }

    @Test
    void intentarActualizarClienteConIdentificacionPersonaYaRegistradaTest() throws BusinessException {
        Integer id = 1;
        Integer idBarrio = 1;
        String identificacion = "1234567890";
        Optional<Persona> persona = Optional.of(PersonaHelper.crearNueva(identificacion));
        Optional<Cliente> cliente = Optional.of(ClienteHelper.crearNuevoCliente(persona.get()));
        Optional<Barrio> barrio = Optional.of(BarrioHelper.construirAleratorio());

        when(clienteRepository.findById(id)).thenReturn(cliente);
        when(personaRepository.findByIdentificacion(identificacion)).thenReturn(persona);
        when(barrioRepository.findById(idBarrio)).thenReturn(barrio);

        BusinessException exception = assertThrows(
                BusinessException.class,
                () -> actualizarClienteUseCase.actualizar(getCreacionDTO(identificacion, idBarrio), id),
                "Se espera excepción"
        );

        assertTrue(exception.getMessage().contains(IDENTIFICACION_YA_REGISTRADA));
    }

    @Test
    void clienteNoEncontradoParaActualizarTest(){
        Integer id = 1;
        Optional<Cliente> cliente = Optional.empty();

        when(clienteRepository.findById(id)).thenReturn(cliente);

        NotFoundException exception = assertThrows(
                NotFoundException.class,
                () -> actualizarClienteUseCase.actualizar(null, id),
                "Se espera excepción"
        );

        assertTrue(exception.getMessage().contains(CLIENTE_NO_ENCONTRADO));

    }

    private ClienteCreacionDto getCreacionDTO(String identificacion, Integer idBarrio){
        return new ClienteCreacionDto(
                identificacion,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                GeneralHelper.obtenerValorBigDecimalAleatorio().toString(),
                idBarrio,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

}