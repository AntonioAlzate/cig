package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cliente.ports.ClienteRepository;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.shared.dtos.CuotaPagoDTO;
import com.uco.cig.usecase.cliente.ActualizarCupoDeudaAbonoCuentaClienteUseCase;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;

@Service
@Transactional
public class PagarCuotaProximaUseCase {

    private static final String NO_REGISTRA_CUOTAS = "La venta consultada no presenta cuotas pendientes";

    private final CuotaRepository cuotaRepository;
    private final ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;
    private final ObtenerClientePorIdUseCase clientePorIdUseCase;
    private final ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaCuentaClienteUseCase;

    public PagarCuotaProximaUseCase(CuotaRepository cuotaRepository, ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase, ObtenerClientePorIdUseCase clientePorIdUseCase, ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaCuentaClienteUseCase) {
        this.cuotaRepository = cuotaRepository;
        this.trabajadorPorIdUseCase = trabajadorPorIdUseCase;
        this.clientePorIdUseCase = clientePorIdUseCase;
        this.actualizarCupoDeudaCuentaClienteUseCase = actualizarCupoDeudaCuentaClienteUseCase;
    }

    public Cuota pagar(CuotaPagoDTO cuotaPagoDTO) {
        // todo: estado cuota
        EstadoCuota estadoCuotaPendiente = new EstadoCuota(1, "PEDIENTE");
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");

        Trabajador trabajadorCobra = trabajadorPorIdUseCase.obtener(cuotaPagoDTO.getIdTrabajador());

        Cuota cuotaPagar = getCuotaProxima(cuotaPagoDTO.getIdVenta(), estadoCuotaPendiente);
        cuotaPagar.setEstadoCuota(estadoCuotaCancelada);
        cuotaPagar.setFechaRealizacion(OffsetDateTime.now());
        cuotaPagar.setTrabajador(trabajadorCobra);
        cuotaPagar.setResta(BigDecimal.ZERO);

        // Actualizar cupo cuenta cliente
        Cliente cliente = clientePorIdUseCase.obtener(cuotaPagoDTO.getIdCliente());
        actualizarCupoDeudaCuentaClienteUseCase.actualizar(cuotaPagar.getValorCobro(), cliente);

        return cuotaRepository.save(cuotaPagar);
    }

    private Cuota getCuotaProxima(Integer idVenta, EstadoCuota estadoCuotaPendiente) {
        Optional<Cuota> cuotaProxima = cuotaRepository.findFirstByIdVentaAndIdEstadoCuotaOrderByFechaPropuesta(idVenta, estadoCuotaPendiente.getId());

        if(cuotaProxima.isEmpty())
            throw new NotFoundException(NO_REGISTRA_CUOTAS);

        return cuotaProxima.get();
    }

}
