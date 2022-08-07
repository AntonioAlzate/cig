package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.cuentacliente.ports.CuentaClienteRepository;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.cuota.ports.CuotaRepository;
import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.detalle.venta.ports.DetalleVentaRepository;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.shared.dtos.CreacionVentaDTO;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.cliente.ValidarActualizarCupoUseCase;
import com.uco.cig.usecase.cuota.CrearPlanCuotasUseCase;
import com.uco.cig.usecase.detalleventa.EnlazarDetallesAVentaYGuardarUseCase;
import com.uco.cig.usecase.detalleventa.GenerarDetallesVentaSinVentaAsociadaUseCase;
import com.uco.cig.usecase.formapago.ObtenerFormaPagoPorIdUseCase;
import com.uco.cig.usecase.modalidad.ObtenerModalidadPorIdUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class RegistarVentaUseCase {

    private static final String VENTA_SIN_PRODUCTOS = "No se puede generar una venta sin ningun producto asociado";


    private final VentaRepository ventaRepository;

    // Use Cases
    private final ObtenerClientePorIdUseCase clientePorIdUseCase;
    private final ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;
    private final ObtenerModalidadPorIdUseCase modalidadPorIdUseCase;
    private final ObtenerEstadoVentaActivaUseCase estadoVentaActivaUseCase;
    private final ObtenerFormaPagoPorIdUseCase formaPagoPorIdUseCase;
    private final GenerarDetallesVentaSinVentaAsociadaUseCase detallesVentaSinVentaAsociadaUseCase;
    private final ValidarActualizarCupoUseCase validarActualizarCupoUseCase;
    private final EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase;
    private final CrearPlanCuotasUseCase planCuotasUseCase;


    public RegistarVentaUseCase(VentaRepository ventaRepository, ObtenerClientePorIdUseCase clientePorIdUseCase, ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase, ObtenerModalidadPorIdUseCase modalidadPorIdUseCase, ObtenerEstadoVentaActivaUseCase estadoVentaActivaUseCase, ObtenerFormaPagoPorIdUseCase formaPagoPorIdUseCase, GenerarDetallesVentaSinVentaAsociadaUseCase detallesVentaSinVentaAsociadaUseCase, ValidarActualizarCupoUseCase validarActualizarCupoUseCase, EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase, CrearPlanCuotasUseCase planCuotasUseCase) {
        this.ventaRepository = ventaRepository;

        this.clientePorIdUseCase = clientePorIdUseCase;
        this.trabajadorPorIdUseCase = trabajadorPorIdUseCase;
        this.modalidadPorIdUseCase = modalidadPorIdUseCase;
        this.estadoVentaActivaUseCase = estadoVentaActivaUseCase;
        this.formaPagoPorIdUseCase = formaPagoPorIdUseCase;
        this.detallesVentaSinVentaAsociadaUseCase = detallesVentaSinVentaAsociadaUseCase;
        this.validarActualizarCupoUseCase = validarActualizarCupoUseCase;
        this.enlazarDetallesAVentaYGuardarUseCase = enlazarDetallesAVentaYGuardarUseCase;
        this.planCuotasUseCase = planCuotasUseCase;
    }

    public Venta realizarVenta(CreacionVentaDTO creacionVentaDTO) throws BusinessException {

        // todo: modalidad contado

        // Existencia de productos en la venta
        if(creacionVentaDTO.getDetallesVenta().isEmpty())
            throw new BadRequestException(VENTA_SIN_PRODUCTOS);

        // Obtención y validación de entidades necesarias para la venta
        Cliente cliente = clientePorIdUseCase.obtener(creacionVentaDTO.getIdCliente());
        Trabajador trabajador = trabajadorPorIdUseCase.obtener(creacionVentaDTO.getIdTrabajador());
        FormaPago formaPago = formaPagoPorIdUseCase.obtener(creacionVentaDTO.getIdFormaPago());
        Modalidad modalidad = modalidadPorIdUseCase.obtener(creacionVentaDTO.getIdModalidad());
        EstadoVenta estadoVenta = estadoVentaActivaUseCase.obtener();

        // Generacion de detalles de venta
        List<DetalleVenta> detallesVenta = detallesVentaSinVentaAsociadaUseCase.generar(creacionVentaDTO.getDetallesVenta(), creacionVentaDTO.getIdModalidad());

        // calculo de valor total de la venta y actualizacion del cupo
        BigDecimal valorTotalCompra = calcularValorTotal(detallesVenta);
        validarActualizarCupoUseCase.validarActualizar(cliente.getCuentaCliente(), valorTotalCompra);

        // Se crea la venta y se guarda
        Venta venta = Venta.nuevo(
                OffsetDateTime.now(),
                valorTotalCompra,
                trabajador,
                formaPago,
                modalidad,
                cliente.getCuentaCliente(),
                estadoVenta
        );
        venta = ventaRepository.save(venta);

        // Se enlazan los detalles y se guardan
        enlazarDetallesAVentaYGuardarUseCase.enlazarGuardar(detallesVenta, venta);

        // Creación plan de cuotas
        planCuotasUseCase.generar(valorTotalCompra, creacionVentaDTO.getCuotaInicial(), formaPago.getNumeroDias(), formaPago.getValorMinimo(), venta);

        return venta;
    }


    private BigDecimal calcularValorTotal(List<DetalleVenta> detallesVenta) {
        BigDecimal valorTotal = BigDecimal.ZERO;

        for (DetalleVenta detalleVenta : detallesVenta) {
            valorTotal = valorTotal.add(detalleVenta.getValorTotal());
        }

        return valorTotal;
    }

}
