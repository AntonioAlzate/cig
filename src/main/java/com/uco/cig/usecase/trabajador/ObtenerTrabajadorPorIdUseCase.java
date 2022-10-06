package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObtenerTrabajadorPorIdUseCase {

    private static final String TRABAJADOR_NO_ENCONTRADO = "El trabajador no ha sido encontrado";

    private final TrabajadorRepository trabajadorRepository;

    public ObtenerTrabajadorPorIdUseCase(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public Trabajador obtener(Integer idTrabajador){
        Optional<Trabajador> trabajador = trabajadorRepository.findById(idTrabajador);

        if(trabajador.isEmpty())
            throw new NotFoundException(TRABAJADOR_NO_ENCONTRADO + " id: " + idTrabajador);

        return trabajador.get();
    }
}
