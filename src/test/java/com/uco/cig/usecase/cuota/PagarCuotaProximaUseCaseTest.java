package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.generate.*;
import com.uco.cig.shared.dtos.CuotaPagoDTO;
import com.uco.cig.usecase.cliente.ActualizarCupoDeudaAbonoCuentaClienteUseCase;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class PagarCuotaProximaUseCaseTest {

    private static final String NO_REGISTRA_CUOTAS = "La venta consultada no presenta cuotas pendientes";

    CuotaRepository cuotaRepository;

    ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;

    ObtenerClientePorIdUseCase clientePorIdUseCase;

    ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaAbonoCuentaClienteUseCase;

    PagarCuotaProximaUseCase pagarCuotaProximaUseCase;

    @BeforeEach
    public void setup(){
        cuotaRepository = mock(CuotaRepository.class);
        trabajadorPorIdUseCase = mock(ObtenerTrabajadorPorIdUseCase.class);
        clientePorIdUseCase = mock(ObtenerClientePorIdUseCase.class);
        actualizarCupoDeudaAbonoCuentaClienteUseCase = mock(
                ActualizarCupoDeudaAbonoCuentaClienteUseCase.class
        );
        pagarCuotaProximaUseCase = new PagarCuotaProximaUseCase(cuotaRepository,
                trabajadorPorIdUseCase,
                clientePorIdUseCase,
                actualizarCupoDeudaAbonoCuentaClienteUseCase
        );
    }

    @Test
    void getCuotaProximaNoRegistraTest(){
        Integer id = 1;
        EstadoCuota estadoCuota = EstadoCuotaHelper.crearEstadoCuota();
        CuotaPagoDTO cuotaPagoDTO = new CuotaPagoDTO(1,1,1);

        when(cuotaRepository.findFirstByIdVentaAndIdEstadoCuotaOrderByFechaPropuesta(
                id,
                estadoCuota.getId())).thenReturn(Optional.empty()
        );

        NotFoundException result = assertThrows(
                NotFoundException.class,
                () -> pagarCuotaProximaUseCase.pagar(cuotaPagoDTO),
                "Se esperaba exepción"
        );

        assertTrue(result.getMessage().contains(NO_REGISTRA_CUOTAS));
    }

    @Test
    void pagarTest() throws BusinessException {
        Cliente cliente = ClienteHelper.crearNuevoCliente();
        Trabajador trabajador = TrabajadorHelper.crearTrabajador(cliente.getPersona().getIdentificacion());
        Venta venta = VentaHelper.crearVenta(trabajador);
        CuotaPagoDTO cuotaPagoDTO = new CuotaPagoDTO(trabajador.getId(), cliente.getId(), venta.getId());

        Cuota cuotaProxima = CuotasHelper.crearCuota(trabajador, new EstadoCuota(1, "PENDIENTE"));
        when(cuotaRepository.findFirstByIdVentaAndIdEstadoCuotaOrderByFechaPropuesta(venta.getId(), cuotaProxima.getEstadoCuota().getId())).thenReturn(Optional.of(cuotaProxima));
        when(cuotaRepository.save(any())).thenReturn(cuotaProxima);

        Cuota result = pagarCuotaProximaUseCase.pagar(cuotaPagoDTO);

        assertEquals(cuotaProxima.getEstadoCuota(), result.getEstadoCuota());
        assertEquals(cuotaProxima.getId(), result.getId());
        assertEquals(cuotaProxima.getValorCobro(), result.getValorCobro());
    }
}