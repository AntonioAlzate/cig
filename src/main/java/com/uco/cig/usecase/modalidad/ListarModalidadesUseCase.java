package com.uco.cig.usecase.modalidad;

import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.modalidad.ports.ModalidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListarModalidadesUseCase {

    private final ModalidadRepository modalidadRepository;

    public ListarModalidadesUseCase(ModalidadRepository modalidadRepository) {
        this.modalidadRepository = modalidadRepository;
    }

    public List<Modalidad> listar() {
        return modalidadRepository.findAll();
    }
}
