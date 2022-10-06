package com.uco.cig.usecase.cuota;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.NotFoundException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.shared.dtos.AbonoPagoDTO;
import com.uco.cig.usecase.cliente.ActualizarCupoDeudaAbonoCuentaClienteUseCase;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class RealizarAbonoCuentaUseCase {

    private static final String ABONO_EXITOSO = "El abono fue exitoso";
    private static final String ABONO_FALLIDO = "Error al realizar abono";
    private static final String SIN_CUOTAS_PENDIENTES = "La venta no tiene cuotas pendientes por lo cual no se puede realizar el abono.";

    private final CuotaRepository cuotaRepository;

    private final ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;
    private final ObtenerClientePorIdUseCase clientePorIdUseCase;
    private final ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaCliente;

    public RealizarAbonoCuentaUseCase(CuotaRepository cuotaRepository, ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase, ObtenerClientePorIdUseCase clientePorIdUseCase, ActualizarCupoDeudaAbonoCuentaClienteUseCase actualizarCupoDeudaCliente) {
        this.cuotaRepository = cuotaRepository;
        this.actualizarCupoDeudaCliente = actualizarCupoDeudaCliente;
        this.trabajadorPorIdUseCase = trabajadorPorIdUseCase;
        this.clientePorIdUseCase = clientePorIdUseCase;
    }

    public String abonar(AbonoPagoDTO abonoPagoDTO) throws BusinessException {
        // todo: estado cuota
        EstadoCuota estadoCuotaPendiente = new EstadoCuota(1, "PEDIENTE");
        EstadoCuota estadoCuotaCancelada = new EstadoCuota(2, "CANCELADA");

        Trabajador trabajadorRealiza = trabajadorPorIdUseCase.obtener(abonoPagoDTO.getIdTrabajador());
        Cliente cliente = clientePorIdUseCase.obtener(abonoPagoDTO.getIdCliente());

        List<Cuota> cuotasPendientes = cuotaRepository.findAllByIdVentaAndIdEstadoCuotaOrderByFechaPropuestaDsc(abonoPagoDTO.getIdVenta(), estadoCuotaPendiente.getId());
        
        if(cuotasPendientes.isEmpty())
            throw new NotFoundException(SIN_CUOTAS_PENDIENTES);

        BigDecimal valorActualAbono = abonoPagoDTO.getValorAbono();

        for (Cuota cuota: cuotasPendientes) {
            int comparacion = valorActualAbono.compareTo(cuota.getResta());

            if(comparacion > 0) {
                valorActualAbono = valorActualAbono.subtract(cuota.getResta());
                guardarCuota(cuota, BigDecimal.ZERO, OffsetDateTime.now(), trabajadorRealiza, estadoCuotaCancelada);
            } else if(comparacion == 0){
                guardarCuota(cuota, BigDecimal.ZERO, OffsetDateTime.now(), trabajadorRealiza, estadoCuotaCancelada);
                break;
            } else {
                BigDecimal restaNueva = cuota.getResta().subtract(valorActualAbono);
                guardarCuota(cuota, restaNueva, null, null, estadoCuotaPendiente);
                break;
            }
        }

        actualizarCupoDeudaCliente.actualizar(abonoPagoDTO.getValorAbono(), cliente);
        
        return ABONO_EXITOSO;
    }


    private void guardarCuota(Cuota cuota, BigDecimal resta, OffsetDateTime fechaRealizacion, Trabajador trabajador, EstadoCuota estadoCuota) throws BusinessException {
        cuota.setResta(resta);
        cuota.setFechaRealizacion(fechaRealizacion);
        cuota.setTrabajador(trabajador);
        cuota.setEstadoCuota(estadoCuota);
        cuotaRepository.save(cuota);
    }
}
