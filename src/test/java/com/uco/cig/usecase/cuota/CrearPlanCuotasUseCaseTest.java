package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.generate.GeneralHelper;
import com.uco.cig.generate.TipoCobroHelper;
import com.uco.cig.generate.TrabajadorHelper;
import com.uco.cig.generate.VentaHelper;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaPendienteUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroInicialUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class CrearPlanCuotasUseCaseTest {

    CuotaRepository cuotaRepository;

    ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase;
    ConsultarTipoCobroInicialUseCase consultarTipoCobroInicialUseCase;
    ConsultarEstadoCuotaPendienteUseCase consultarEstadoCuotaPendienteUseCase;
    ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase;
    CrearPlanCuotasUseCase crearPlanCuotasUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        consultarTipoCobroNormalUseCase = mock(ConsultarTipoCobroNormalUseCase.class);
        consultarTipoCobroInicialUseCase = mock(ConsultarTipoCobroInicialUseCase.class);
        consultarEstadoCuotaPendienteUseCase = mock(ConsultarEstadoCuotaPendienteUseCase.class);
        consultarEstadoCuotaCanceladaUseCase = mock(ConsultarEstadoCuotaCanceladaUseCase.class);
        crearPlanCuotasUseCase = new CrearPlanCuotasUseCase(cuotaRepository, consultarTipoCobroNormalUseCase, consultarTipoCobroInicialUseCase, consultarEstadoCuotaPendienteUseCase, consultarEstadoCuotaCanceladaUseCase);
    }

    @Test
    void crearPlanCuotasTest() throws BusinessException {
        List<Cuota> cuotasGeneradas = new ArrayList<>();
        EstadoCuota estadoCuotaPendiente = new EstadoCuota(1, "PENDIENTE");
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");

        BigDecimal valorTotalCompra = GeneralHelper.obtenerValorBigDecimalAleatorio();
        BigDecimal valorCuotaInicial = GeneralHelper.obtenerValorBigDecimalAleatorio();
        Integer dias = 10;
        BigDecimal valorMinimo = GeneralHelper.obtenerValorBigDecimalAleatorio();
        Venta venta = VentaHelper.crearVenta();
        Trabajador trabajador = TrabajadorHelper.crearTrabajador();

        Cuota cuotaInicial = Cuota.nueva(
                valorCuotaInicial,
                BigDecimal.ZERO,
                LocalDate.now(),
                OffsetDateTime.now(),
                venta,
                trabajador,
                TipoCobroHelper.crearTipoCobro(),
                estadoCuotaCancelada
        );
        Cuota cuota = Cuota.nueva(
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                LocalDate.now(),
                null,
                VentaHelper.crearVenta(),
                null,
                TipoCobroHelper.crearTipoCobro(),
                estadoCuotaPendiente
        );
        cuotasGeneradas.add(cuotaInicial);
        cuotasGeneradas.add(cuota);

        when(cuotaRepository.saveAll(any())).thenReturn(cuotasGeneradas);

        crearPlanCuotasUseCase.generar(valorTotalCompra,valorCuotaInicial,dias,BigDecimal.ONE,venta,trabajador);

        Mockito.verify(cuotaRepository).saveAll(any());
    }

}