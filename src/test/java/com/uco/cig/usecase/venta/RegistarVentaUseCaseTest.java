package com.uco.cig.usecase.venta;

import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.cliente.ValidarActualizarCupoUseCase;
import com.uco.cig.usecase.cuota.CrearCuotaPagoContadoUseCase;
import com.uco.cig.usecase.cuota.CrearPlanCuotasUseCase;
import com.uco.cig.usecase.detalleventa.EnlazarDetallesAVentaYGuardarUseCase;
import com.uco.cig.usecase.detalleventa.GenerarDetallesVentaSinVentaAsociadaUseCase;
import com.uco.cig.usecase.formapago.ObtenerFormaPagoPorIdUseCase;
import com.uco.cig.usecase.modalidad.ObtenerModalidadPorIdUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class RegistarVentaUseCaseTest {

    VentaRepository ventaRepository;

    ObtenerClientePorIdUseCase clientePorIdUseCase;
    ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;
    ObtenerModalidadPorIdUseCase modalidadPorIdUseCase;
    ObtenerEstadoVentaActivaUseCase estadoVentaActivaUseCase;
    ObtenerEstadoVentaCanceladaUseCase estadoVentaCanceladaUseCase;
    ObtenerFormaPagoPorIdUseCase formaPagoPorIdUseCase;
    GenerarDetallesVentaSinVentaAsociadaUseCase detallesVentaSinVentaAsociadaUseCase;
    ValidarActualizarCupoUseCase validarActualizarCupoUseCase;
    EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase;
    CrearPlanCuotasUseCase planCuotasUseCase;
    CrearCuotaPagoContadoUseCase cuotaPagoContadoUseCase;

    @BeforeEach
    void setup() {
        ventaRepository = mock(VentaRepository.class);
        clientePorIdUseCase = mock(ObtenerClientePorIdUseCase.class);
        trabajadorPorIdUseCase = mock(ObtenerTrabajadorPorIdUseCase.class);
        modalidadPorIdUseCase = mock(ObtenerModalidadPorIdUseCase.class);
        estadoVentaActivaUseCase = mock(ObtenerEstadoVentaActivaUseCase.class);
        estadoVentaCanceladaUseCase = mock(ObtenerEstadoVentaCanceladaUseCase.class);
        formaPagoPorIdUseCase = mock(ObtenerFormaPagoPorIdUseCase.class);
        detallesVentaSinVentaAsociadaUseCase = mock(GenerarDetallesVentaSinVentaAsociadaUseCase.class);
        validarActualizarCupoUseCase = mock(ValidarActualizarCupoUseCase.class);
        enlazarDetallesAVentaYGuardarUseCase = mock(EnlazarDetallesAVentaYGuardarUseCase.class);
    }
}