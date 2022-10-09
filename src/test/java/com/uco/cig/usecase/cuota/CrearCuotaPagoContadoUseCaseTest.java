package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.generate.GeneralHelper;
import com.uco.cig.generate.TrabajadorHelper;
import com.uco.cig.generate.VentaHelper;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

import static org.mockito.Mockito.*;

class CrearCuotaPagoContadoUseCaseTest {

    CuotaRepository cuotaRepository;
    ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase;
    ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase;
    CrearCuotaPagoContadoUseCase crearCuotaPagoContadoUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        consultarTipoCobroNormalUseCase = mock(ConsultarTipoCobroNormalUseCase.class);
        consultarEstadoCuotaCanceladaUseCase = mock(ConsultarEstadoCuotaCanceladaUseCase.class);
        crearCuotaPagoContadoUseCase = new CrearCuotaPagoContadoUseCase(cuotaRepository, consultarTipoCobroNormalUseCase, consultarEstadoCuotaCanceladaUseCase);
    }

    @Test
    void crearCuotaPagoContadoTest() throws BusinessException {
        BigDecimal valorTotalCompra = GeneralHelper.obtenerValorBigDecimalAleatorio();
        Venta venta = VentaHelper.crearVenta();
        Trabajador trabajador = TrabajadorHelper.crearTrabajador();

        TipoCobro tipoCobroNormal = TipoCobro.construir(2, "NORMAL");
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

        when(cuotaRepository.save(any())).thenReturn(cuota);

        crearCuotaPagoContadoUseCase.generar(valorTotalCompra,venta,trabajador);

        Mockito.verify(cuotaRepository).save(any());
    }

}