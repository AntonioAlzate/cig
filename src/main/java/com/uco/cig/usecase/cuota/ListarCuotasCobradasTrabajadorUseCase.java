package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ListarCuotasCobradasTrabajadorUseCase {

    private final CuotaRepository cuotaRepository;

    public ListarCuotasCobradasTrabajadorUseCase(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public List<Cuota> obtener(Integer idTrabajador, OffsetDateTime fechaRealizacion) {
        //todo
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");
        return cuotaRepository.findAllByIdTrabajadorAndIdEstadoCuotaAndFechaRealizacion(idTrabajador, estadoCuotaCancelada.getId(), fechaRealizacion);
    }
}
