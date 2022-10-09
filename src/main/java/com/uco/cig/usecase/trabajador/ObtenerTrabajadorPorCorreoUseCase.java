package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ObtenerTrabajadorPorCorreoUseCase {

    private static final String TRABAJADOR_NO_ENCONTRADO = "No se ha encontrado un trabajador con el correo especificado";
    private final TrabajadorRepository trabajadorRepository;

    public ObtenerTrabajadorPorCorreoUseCase(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public Trabajador obtener(String correo) {
        Optional<Trabajador> trabajador = trabajadorRepository.findByCorreo(correo);

        if(trabajador.isEmpty())
            throw new NotFoundException(TRABAJADOR_NO_ENCONTRADO + " " + correo);

        return trabajador.get();
    }
}
