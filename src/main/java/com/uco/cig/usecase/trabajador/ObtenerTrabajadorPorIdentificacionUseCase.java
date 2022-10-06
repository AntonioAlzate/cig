package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import org.springframework.stereotype.Service;

@Service
public class ObtenerTrabajadorPorIdentificacionUseCase {

    private final TrabajadorRepository trabajadorRepository;

    public ObtenerTrabajadorPorIdentificacionUseCase(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public Trabajador obtener(String identificacion){
        return trabajadorRepository.findByIdentificacion(identificacion.trim());
    }
}
