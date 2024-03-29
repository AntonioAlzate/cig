package com.uco.cig.usecase.trabajador;

import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.trabajador.ports.TrabajadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarTrabajadoresUseCase {

    private final TrabajadorRepository trabajadorRepository;

    public ListarTrabajadoresUseCase(TrabajadorRepository trabajadorRepository) {
        this.trabajadorRepository = trabajadorRepository;
    }

    public List<Trabajador> listar(){
        return trabajadorRepository.findAll();
    }
}
