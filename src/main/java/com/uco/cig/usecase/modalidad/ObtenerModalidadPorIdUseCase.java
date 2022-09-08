package com.uco.cig.usecase.modalidad;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.modalidad.ports.ModalidadRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ObtenerModalidadPorIdUseCase {

    private static final String MODALIDAD_NO_ENCONTRADA = "La Modalidad de pago no ha sido encontrada";

    private final ModalidadRepository modalidadRepository;

    public ObtenerModalidadPorIdUseCase(ModalidadRepository modalidadRepository) {
        this.modalidadRepository = modalidadRepository;
    }

    public Modalidad obtener(Integer idModalidad) {
        Optional<Modalidad> modalidad = modalidadRepository.findById(idModalidad);

        if(modalidad.isEmpty())
            throw new NotFoundException(MODALIDAD_NO_ENCONTRADA + " id: " + idModalidad);

        return modalidad.get();
    }
}
