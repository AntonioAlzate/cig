package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import com.uco.cig.shared.dtos.TrabajadorCreacionDto;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ActualizarTrabajadorUseCase {

    private static final String TRABAJADOR_NO_ENCONTRADO = "El trabajador que intenta actualizar no se ha encontrado";
    private static final String BARRIO_REQUERIDO = "No es posible actualizar un cliente sin especificar un barrio valido";
    private static final String IDENTIFICACION_YA_REGISTRADA = "La identificación ingresada ya se encuentra registrada en otro usuario por lo tanto no se puede actualizar";

    private final TrabajadorRepository trabajadorRepository;
    private final BarrioRepository barrioRepository;
    private final PersonaRepository personaRepository;

    public ActualizarTrabajadorUseCase(TrabajadorRepository trabajadorRepository, BarrioRepository barrioRepository, PersonaRepository personaRepository) {
        this.trabajadorRepository = trabajadorRepository;
        this.barrioRepository = barrioRepository;
        this.personaRepository = personaRepository;
    }

    public Trabajador actualizar(TrabajadorCreacionDto creacionDto, Integer id) throws BusinessException {
        Optional<Trabajador> trabajador = trabajadorRepository.findById(id);

        if(trabajador.isEmpty())
            throw new NotFoundException(TRABAJADOR_NO_ENCONTRADO);

        trabajador = pasarDatosDTO(trabajador.get(), creacionDto);
        
        if(yaExistePersona(trabajador.get().getPersona().getIdentificacion()))
            throw new BusinessException(IDENTIFICACION_YA_REGISTRADA);

        return trabajadorRepository.save(trabajador.get());
    }

    private Optional<Trabajador> pasarDatosDTO(Trabajador trabajador, TrabajadorCreacionDto creacionDto) throws BusinessException {

        Barrio barrio = barrioRepository.findById(creacionDto.getIdBarrio()).orElse(null);

        if(barrio == null)
            throw new BusinessException(BARRIO_REQUERIDO);

        trabajador.getPersona().setIdentificacion(creacionDto.getIdentificacion());
        trabajador.getPersona().setPrimerNombre(creacionDto.getPrimerNombre());
        trabajador.getPersona().setSegundoNombre(creacionDto.getSegundoNombre());
        trabajador.getPersona().setPrimerApellido(creacionDto.getPrimerApellido());
        trabajador.getPersona().setSegundoApellido(creacionDto.getSegundoApellido());
        trabajador.getPersona().setDireccion(creacionDto.getDireccion());
        trabajador.getPersona().setTelefono(creacionDto.getTelefono());
        trabajador.getPersona().setBarrio(barrio);

        Trabajador trabajadorValidado =
                Trabajador.construir(
                        trabajador.getId(),
                        trabajador.getPersona(),
                        trabajador.getEstado()
                );

        return Optional.of(trabajadorValidado);
    }

    private boolean yaExistePersona(String identificacion) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacion);

        return persona != null;
    }
}
