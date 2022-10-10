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

import java.util.Optional;

@Service
public class ActualizarTrabajadorUseCase {

    private static final String TRABAJADOR_NO_ENCONTRADO = "El trabajador que intenta actualizar no se ha encontrado";
    private static final String BARRIO_REQUERIDO = "No es posible actualizar un cliente sin especificar un barrio valido";
    private static final String IDENTIFICACION_YA_REGISTRADA = "La identificación ingresada ya se encuentra registrada en otro usuario por lo tanto no se puede actualizar";
    private static final String TRABAJADOR_CON_CORREO_YA_REGISTRADO = "Ya se encuentra registrado en el sistema un trabajador con correo:";

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
        
        if(yaExistePersona(trabajador.get().getPersona().getIdentificacion(), id))
            throw new BusinessException(IDENTIFICACION_YA_REGISTRADA);

        return trabajadorRepository.update(trabajador.get());
    }

    private Optional<Trabajador> pasarDatosDTO(Trabajador trabajador, TrabajadorCreacionDto creacionDto) throws BusinessException {

        Barrio barrio = barrioRepository.findById(creacionDto.getIdBarrio()).orElse(null);

        if(yaExisteTrabajadorConCorreo(creacionDto.getCorreo(), trabajador)){
            throw new BusinessException(TRABAJADOR_CON_CORREO_YA_REGISTRADO + " " + creacionDto.getCorreo());
        }

        if(barrio == null)
            throw new BusinessException(BARRIO_REQUERIDO);

        trabajador.getPersona().setIdentificacion(creacionDto.getIdentificacion().trim().toUpperCase());
        trabajador.getPersona().setPrimerNombre(creacionDto.getPrimerNombre().trim().toUpperCase());
        trabajador.getPersona().setSegundoNombre(creacionDto.getSegundoNombre().trim().toUpperCase());
        trabajador.getPersona().setPrimerApellido(creacionDto.getPrimerApellido().trim().toUpperCase());
        trabajador.getPersona().setSegundoApellido(creacionDto.getSegundoApellido().trim().toUpperCase());
        trabajador.getPersona().setDireccion(creacionDto.getDireccion().trim().toUpperCase());
        trabajador.getPersona().setTelefono(creacionDto.getTelefono().trim());
        trabajador.getPersona().setBarrio(barrio);
        trabajador.setCorreo(creacionDto.getCorreo().trim());

        Trabajador trabajadorValidado =
                Trabajador.construir(
                        trabajador.getId(),
                        trabajador.getPersona(),
                        trabajador.getEstado(),
                        trabajador.getCorreo()
                );

        return Optional.of(trabajadorValidado);
    }

    private boolean yaExisteTrabajadorConCorreo(String correo, Trabajador trabajador) {
        Optional<Trabajador> trabajadorDespues = trabajadorRepository.findByCorreo(correo);

        if(trabajadorDespues.isEmpty())
            return false;

        return !trabajador.getId().equals(trabajadorDespues.get().getId());
    }

    private boolean yaExistePersona(String identificacion, Integer id) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacion.trim());
        Optional<Trabajador> trabajador = trabajadorRepository.findById(id);

        if(persona.isEmpty())
          return false;

        return !persona.get().getId().equals(trabajador.get().getPersona().getId());
    }
}
