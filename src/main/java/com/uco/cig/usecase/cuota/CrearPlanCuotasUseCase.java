package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrearPlanCuotasUseCase {

    private final CuotaRepository cuotaRepository;

    public CrearPlanCuotasUseCase(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }

    public void generar(BigDecimal valorTotalCompra, BigDecimal valorCuotaInicial, Integer numeroDias, BigDecimal valorMinimo, Venta venta, Trabajador trabajador) throws BusinessException {
        List<Cuota> cuotasGeneradas = new ArrayList<>();

        // todo: A REVISAR EL TIPO COBRO y estado cuota
        TipoCobro tipoCobroNormal = TipoCobro.Construir(2, "NORMAL");
        TipoCobro tipoCobroInicial = TipoCobro.Construir(1, "INICIAL");
        EstadoCuota estadoCuotaPendiente = new EstadoCuota(1, "PENDIENTE");
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");


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
