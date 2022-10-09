package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
public class CrearCuotaPagoContadoUseCase {

    private final CuotaRepository cuotaRepository;
    private final ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase;
    private final ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase;

    public CrearCuotaPagoContadoUseCase(CuotaRepository cuotaRepository, ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase, ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase) {
        this.cuotaRepository = cuotaRepository;
        this.consultarTipoCobroNormalUseCase = consultarTipoCobroNormalUseCase;
        this.consultarEstadoCuotaCanceladaUseCase = consultarEstadoCuotaCanceladaUseCase;
    }


    public void generar(BigDecimal valorTotalCompra, Venta venta, Trabajador trabajador) throws BusinessException {

        TipoCobro tipoCobroNormal = consultarTipoCobroNormalUseCase.consultar();
        EstadoCuota estadoCuotaCancelada = consultarEstadoCuotaCanceladaUseCase.consultar();

        LocalDate fechaActual = LocalDate.now();

        // CreacionCuotaInicial
        Cuota cuota = Cuota.nueva(
                valorTotalCompra,
                BigDecimal.ZERO,
                fechaActual,
                OffsetDateTime.now(),
                venta,
                trabajador,
                tipoCobroNormal,
                estadoCuotaCancelada
        );

        cuotaRepository.save(cuota);
    }
}
