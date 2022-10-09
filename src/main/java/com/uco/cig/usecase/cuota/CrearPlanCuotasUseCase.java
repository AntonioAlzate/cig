package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaPendienteUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroInicialUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrearPlanCuotasUseCase {

    private final CuotaRepository cuotaRepository;

    private final ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase;
    private final ConsultarTipoCobroInicialUseCase consultarTipoCobroInicialUseCase;
    private final ConsultarEstadoCuotaPendienteUseCase consultarEstadoCuotaPendienteUseCase;
    private final ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase;

    public CrearPlanCuotasUseCase(CuotaRepository cuotaRepository, ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase, ConsultarTipoCobroInicialUseCase consultarTipoCobroInicialUseCase, ConsultarEstadoCuotaPendienteUseCase consultarEstadoCuotaPendienteUseCase, ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase) {
        this.cuotaRepository = cuotaRepository;
        this.consultarTipoCobroNormalUseCase = consultarTipoCobroNormalUseCase;
        this.consultarTipoCobroInicialUseCase = consultarTipoCobroInicialUseCase;
        this.consultarEstadoCuotaPendienteUseCase = consultarEstadoCuotaPendienteUseCase;
        this.consultarEstadoCuotaCanceladaUseCase = consultarEstadoCuotaCanceladaUseCase;
    }

    public void generar(BigDecimal valorTotalCompra, BigDecimal valorCuotaInicial, Integer numeroDias, BigDecimal valorMinimo, Venta venta, Trabajador trabajador) throws BusinessException {
        List<Cuota> cuotasGeneradas = new ArrayList<>();

        TipoCobro tipoCobroNormal = consultarTipoCobroNormalUseCase.consultar();
        TipoCobro tipoCobroInicial = consultarTipoCobroInicialUseCase.consultar();
        EstadoCuota estadoCuotaPendiente = consultarEstadoCuotaPendienteUseCase.consultar();
        EstadoCuota estadoCuotaCancelada = consultarEstadoCuotaCanceladaUseCase.consultar();


        BigDecimal valorFinanciar = valorTotalCompra.subtract(valorCuotaInicial);
        LocalDate fechaPropuesta = LocalDate.now();

        // CreacionCuotaInicial
        Cuota cuotaInicial = Cuota.nueva(
                valorCuotaInicial,
                BigDecimal.ZERO,
                fechaPropuesta,
                OffsetDateTime.now(),
                venta,
                trabajador,
                tipoCobroInicial,
                estadoCuotaCancelada
        );

        cuotasGeneradas.add(cuotaInicial);

        // Cuotas financiamiento
        Integer numeroCuotas = valorFinanciar.divide(valorMinimo).setScale(0, RoundingMode.UP).intValue();


        for (int i = 0; i < numeroCuotas; i++) {
            fechaPropuesta = fechaPropuesta.plusDays(numeroDias);

            Cuota cuota = Cuota.nueva(
                    valorMinimo,
                    valorMinimo,
                    fechaPropuesta,
                    null,
                    venta,
                    null,
                    tipoCobroNormal,
                    estadoCuotaPendiente
            );

            cuotasGeneradas.add(cuota);
        }

        cuotaRepository.saveAll(cuotasGeneradas);
    }
}
