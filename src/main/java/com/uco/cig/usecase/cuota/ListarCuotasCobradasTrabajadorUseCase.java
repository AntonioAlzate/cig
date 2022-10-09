package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class ListarCuotasCobradasTrabajadorUseCase {

    private final CuotaRepository cuotaRepository;
    private final ConsultarEstadoCuotaCanceladaUseCase estadoCuotaCanceladaUseCase;

    public ListarCuotasCobradasTrabajadorUseCase(CuotaRepository cuotaRepository, ConsultarEstadoCuotaCanceladaUseCase estadoCuotaCanceladaUseCase) {
        this.cuotaRepository = cuotaRepository;
        this.estadoCuotaCanceladaUseCase = estadoCuotaCanceladaUseCase;
    }

    public List<Cuota> obtener(Integer idTrabajador, OffsetDateTime fechaRealizacion) {
        EstadoCuota estadoCuotaCancelada = estadoCuotaCanceladaUseCase.consultar();
        return cuotaRepository.findAllByIdTrabajadorAndIdEstadoCuotaAndFechaRealizacion(idTrabajador, estadoCuotaCancelada.getId(), fechaRealizacion);
    }
}
