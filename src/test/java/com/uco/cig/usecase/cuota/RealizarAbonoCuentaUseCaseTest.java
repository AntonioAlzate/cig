package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.generate.ClienteHelper;
import com.uco.cig.generate.CuotasHelper;
import com.uco.cig.generate.GeneralHelper;
import com.uco.cig.generate.TrabajadorHelper;
import com.uco.cig.shared.dtos.AbonoPagoDTO;
import com.uco.cig.usecase.cliente.ActualizarCupoDeudaAbonoCuentaClienteUseCase;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaCanceladaUseCase;
import com.uco.cig.usecase.cuota.estado.ConsultarEstadoCuotaPendienteUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import com.uco.cig.usecase.liquidacion.ConsultarEstadoLiquidacionCanceladaUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RealizarAbonoCuentaUseCaseTest {

    private static final String ABONO_EXITOSO = "El abono fue exitoso";
    private static final String ABONO_FALLIDO = "Error al realizar abono";
    private static final String SIN_CUOTAS_PENDIENTES = "La venta no tiene cuotas pendientes por lo cual no se puede realizar el abono.";

    CuotaRepository cuotaRepository;

    ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;

    ObtenerClientePorIdUseCase clientePorIdUseCase;

    ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaAbonoCuentaClienteUseCase;

    RealizarAbonoCuentaUseCase realizarAbonoCuentaUseCase;

    ConsultarEstadoCuotaCanceladaUseCase consultarEstadoCuotaCanceladaUseCase;
    ConsultarEstadoCuotaPendienteUseCase consultarEstadoCuotaPendienteUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        trabajadorPorIdUseCase = mock(ObtenerTrabajadorPorIdUseCase.class);
        clientePorIdUseCase = mock(ObtenerClientePorIdUseCase.class);
        actualizarCupoDeudaAbonoCuentaClienteUseCase = mock(ActualizarCupoDeudaAbonoCuentaClienteUseCase.class);
        consultarEstadoCuotaPendienteUseCase = mock(ConsultarEstadoCuotaPendienteUseCase.class);
        consultarEstadoCuotaCanceladaUseCase = mock(ConsultarEstadoCuotaCanceladaUseCase.class);
        realizarAbonoCuentaUseCase = new RealizarAbonoCuentaUseCase(
                cuotaRepository,
                trabajadorPorIdUseCase,
                clientePorIdUseCase,
                actualizarCupoDeudaAbonoCuentaClienteUseCase,
                consultarEstadoCuotaCanceladaUseCase,
                consultarEstadoCuotaPendienteUseCase);
    }

    @Test
    void cuentasPendientesExepcionTest(){
        AbonoPagoDTO abonoPagoDTO = new AbonoPagoDTO(
                1,
                1,
                1,
                GeneralHelper.obtenerValorBigDecimalAleatorio()
        );

        List<Cuota> emptyList = Arrays.asList();

        when(cuotaRepository.findAllByIdVentaAndIdEstadoCuotaOrderByFechaPropuestaDsc(1,1)).thenReturn(emptyList);

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> realizarAbonoCuentaUseCase.abonar(abonoPagoDTO),
                "Se esperaba Exepción"
        );

        assertTrue(result.getMessage().contains(SIN_CUOTAS_PENDIENTES));
    }

    @Test
    void  realizarAbonoExitosoTest() throws BusinessException {
        Cliente cliente = ClienteHelper.crearNuevoCliente();
        Trabajador trabajador = TrabajadorHelper.crearTrabajador(cliente.getPersona().getIdentificacion());
        AbonoPagoDTO abonoPagoDTO = new AbonoPagoDTO(
                trabajador.getId(),
                cliente.getId(),
                1,
                GeneralHelper.obtenerValorBigDecimalAleatorio()
        );

        List<Cuota> cuotas = Arrays.asList(
                CuotasHelper.crearCuota(trabajador,new EstadoCuota(1, "PENDIENTE")),
                CuotasHelper.crearCuota(trabajador,new EstadoCuota(2, "PENDIENTE")),
                CuotasHelper.crearCuota(trabajador,new EstadoCuota(3, "PENDIENTE"))
        );

        when(cuotaRepository.findAllByIdVentaAndIdEstadoCuotaOrderByFechaPropuestaDsc(1, 1)).thenReturn(cuotas);

        String result = realizarAbonoCuentaUseCase.abonar(abonoPagoDTO);

        assertEquals(ABONO_EXITOSO, result);
    }

}