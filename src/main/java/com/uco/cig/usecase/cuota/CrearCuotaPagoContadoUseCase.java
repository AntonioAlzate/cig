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
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Service
@Transactional
public class CrearCuotaPagoContadoUseCase {

    private final CuotaRepository cuotaRepository;

    public CrearCuotaPagoContadoUseCase(CuotaRepository cuotaRepository) {
        this.cuotaRepository = cuotaRepository;
    }


    public void generar(BigDecimal valorTotalCompra, Venta venta, Trabajador trabajador) throws BusinessException {

        // todo: A REVISAR EL TIPO COBRO y estado cuota
        TipoCobro tipoCobroNormal = TipoCobro.Construir(2, "NORMAL");
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");

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
