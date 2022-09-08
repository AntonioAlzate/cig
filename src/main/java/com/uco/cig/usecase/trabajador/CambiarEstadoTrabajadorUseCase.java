package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.EstadoEnum;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class CambiarEstadoTrabajadorUseCase {

    private static final String ACTIVO = EstadoEnum.ACTIVO.name();
    private static final String INACTIVO = EstadoEnum.INACTIVO.name();
    private static final String TRABAJADOR_NO_ENCONTRADO = "El trabajador al que desea actualizar el estado no existe.";

    private final TrabajadorRepository trabajadorRepository;

    public CambiarEstadoTrabajadorUseCase(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public Trabajador cambiarEstado(Integer idTrabajador){

        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);

        if(trabajador.isEmpty()){
            throw new NotFoundException(TRABAJADOR_NO_ENCONTRADO);
        }

        String estadoActual = trabajador.get().getEstado().getNombre();
        String estadoNuevo = estadoActual.equals(ACTIVO) ? INACTIVO : ACTIVO;

        return trabajadorRepository.cambiarEstado(idTrabajador, estadoNuevo);
    }
}
