package com.uco.cig.usecase.trabajador;


import com.uco.cig.domain.barrio.Barrio;
import com.uco.cig.domain.barrio.ports.BarrioRepository;
import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.Estado;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.estado.ports.EstadoRepository;
import com.uco.cig.domain.persona.Persona;
import com.uco.cig.domain.persona.ports.PersonaRepository;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import com.uco.cig.shared.dtos.TrabajadorCreacionDto;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CrearTrabajadorUseCase {

    private static final String TRABAJADOR_YA_REGISTRADO = "El trabajador que desea ingresar ya se encuentra registrado";
    private static final String BARRIO_NO_ENCONTRADO = "El Barrio especificado no ha sido encontrado, asegurese de que se encuentre registrado";
    private static final String ESTADO_NO_ENCONTRADO = "El Estado especificado no ha sido encontrado, asegurese de que se encuentre registrado";


    private final PersonaRepository personaRepository;
    private final EstadoRepository estadoRepository;
    private final TrabajadorRepository trabajadorRepository;
    private final BarrioRepository barrioRepository;

    public CrearTrabajadorUseCase(PersonaRepository personaRepository, EstadoRepository estadoRepository, TrabajadorRepository trabajadorRepository, BarrioRepository barrioRepository) {
        this.personaRepository = personaRepository;
        this.estadoRepository = estadoRepository;
        this.trabajadorRepository = trabajadorRepository;
        this.barrioRepository = barrioRepository;
    }

    public Trabajador crear(TrabajadorCreacionDto creacionDto) throws BusinessException {
        if(yaExisteTrabajador(creacionDto.getIdentificacion()))
            throw new BusinessException(TRABAJADOR_YA_REGISTRADO);

        Optional<Barrio> barrio =
                Optional.ofNullable(barrioRepository.findById(creacionDto.getIdBarrio()).orElseThrow(() -> new NotFoundException(BARRIO_NO_ENCONTRADO)));

        Optional<Estado> estado =
                Optional.ofNullable(estadoRepository.findByNombre(EstadoEnum.ACTIVO.toString()).orElseThrow(() -> new NotFoundException(ESTADO_NO_ENCONTRADO)));

        Trabajador trabajador = Trabajador.nuevo(
                creacionDto.getIdentificacion().trim(),
                creacionDto.getPrimerNombre().trim().toUpperCase(),
                creacionDto.getSegundoNombre().trim().toUpperCase(),
                creacionDto.getPrimerApellido().trim().toUpperCase(),
                creacionDto.getSegundoApellido().trim().toUpperCase(),
                creacionDto.getDireccion().trim().toUpperCase(),
                creacionDto.getTelefono().trim(),
                barrio.orElse(null),
                estado.orElse(null)
        );

        return trabajadorRepository.save(trabajador);
    }

    private boolean yaExisteTrabajador(String identificacion) {
        Optional<Persona> persona = personaRepository.findByIdentificacion(identificacion.trim());

        if(persona.isEmpty()){
            return false;
        }

        return trabajadorRepository.existsByIdPersona(persona.orElse(null));
    }
}
