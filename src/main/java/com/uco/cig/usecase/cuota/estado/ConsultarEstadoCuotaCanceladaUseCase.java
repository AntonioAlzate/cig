package com.uco.cig.usecase.cuota.estado;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.estado.cuota.ports.EstadoCuotaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsultarEstadoCuotaCanceladaUseCase {

    private static final String ESTADO_CUOTA_CANCELADA = "PENDIENTE";
    private static final String ESTADO_CUOTA_CANCELADA_NO_EXISTE = "El estado cuota pendiente no existe en el sistema";

    private final EstadoCuotaRepository estadoCuotaRepository;

    public ConsultarEstadoCuotaCanceladaUseCase(EstadoCuotaRepository estadoCuotaRepository) {
        this.estadoCuotaRepository = estadoCuotaRepository;
    }

    public EstadoCuota consultar(){
        Optional<EstadoCuota> estadoCuota = estadoCuotaRepository.findByNombre(ESTADO_CUOTA_CANCELADA);

        if (estadoCuota.isEmpty())
            throw new NotFoundException(ESTADO_CUOTA_CANCELADA_NO_EXISTE);

        return estadoCuota.get();
    }
}
