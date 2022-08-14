package com.uco.cig.usecase.venta;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.cliente.Cliente;
import com.uco.cig.domain.detalle.venta.DetalleVenta;
import com.uco.cig.domain.estado.ventas.EstadoVenta;
import com.uco.cig.domain.formapago.FormaPago;
import com.uco.cig.domain.modalidad.Modalidad;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.domain.venta.ports.VentaRepository;
import com.uco.cig.shared.dtos.CreacionVentaDTO;
import com.uco.cig.usecase.cliente.ObtenerClientePorIdUseCase;
import com.uco.cig.usecase.cliente.ValidarActualizarCupoUseCase;
import com.uco.cig.usecase.cuota.CrearCuotaPagoContadoUseCase;
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

    private static final String MODALIDAD_CREDITO = "CREDITO";
    private static final String MODALIDAD_CONTADO = "CONTADO";
    private static final String PAGO_CONTADO = "CONTADO";
    private static final String VENTA_CREDITO_CON_FORMA_PAGO_INCORRECTA = "Esta tratando de generar una venta a credito con una forma de pago de contado.";
    private static final String VENTA_CONTADO_CON_FORMA_PAGO_INCORRECTA = "Esta tratando de generar una venta de contado con una forma de pago que no es de contado.";

    private final VentaRepository ventaRepository;

    // Use Cases
    private final ObtenerClientePorIdUseCase clientePorIdUseCase;
    private final ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase;
    private final ObtenerModalidadPorIdUseCase modalidadPorIdUseCase;
    private final ObtenerEstadoVentaActivaUseCase estadoVentaActivaUseCase;
    private final ObtenerEstadoVentaCanceladaUseCase estadoVentaCanceladaUseCase;
    private final ObtenerFormaPagoPorIdUseCase formaPagoPorIdUseCase;
    private final GenerarDetallesVentaSinVentaAsociadaUseCase detallesVentaSinVentaAsociadaUseCase;
    private final ValidarActualizarCupoUseCase validarActualizarCupoUseCase;
    private final EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase;
    private final CrearPlanCuotasUseCase planCuotasUseCase;
    private final CrearCuotaPagoContadoUseCase cuotaPagoContadoUseCase;


    public RegistarVentaUseCase(VentaRepository ventaRepository, ObtenerClientePorIdUseCase clientePorIdUseCase, ObtenerTrabajadorPorIdUseCase trabajadorPorIdUseCase, ObtenerModalidadPorIdUseCase modalidadPorIdUseCase, ObtenerEstadoVentaActivaUseCase estadoVentaActivaUseCase, ObtenerEstadoVentaCanceladaUseCase estadoVentaCanceladaUseCase, ObtenerFormaPagoPorIdUseCase formaPagoPorIdUseCase, GenerarDetallesVentaSinVentaAsociadaUseCase detallesVentaSinVentaAsociadaUseCase, ValidarActualizarCupoUseCase validarActualizarCupoUseCase, EnlazarDetallesAVentaYGuardarUseCase enlazarDetallesAVentaYGuardarUseCase, CrearPlanCuotasUseCase planCuotasUseCase, CrearCuotaPagoContadoUseCase cuotaPagoContadoUseCase) {
        this.ventaRepository = ventaRepository;

        this.clientePorIdUseCase = clientePorIdUseCase;
        this.trabajadorPorIdUseCase = trabajadorPorIdUseCase;
        this.modalidadPorIdUseCase = modalidadPorIdUseCase;
        this.estadoVentaActivaUseCase = estadoVentaActivaUseCase;
        this.estadoVentaCanceladaUseCase = estadoVentaCanceladaUseCase;
        this.formaPagoPorIdUseCase = formaPagoPorIdUseCase;
        this.detallesVentaSinVentaAsociadaUseCase = detallesVentaSinVentaAsociadaUseCase;
        this.validarActualizarCupoUseCase = validarActualizarCupoUseCase;
        this.enlazarDetallesAVentaYGuardarUseCase = enlazarDetallesAVentaYGuardarUseCase;
        this.planCuotasUseCase = planCuotasUseCase;
        this.cuotaPagoContadoUseCase = cuotaPagoContadoUseCase;
    }

    public Venta realizarVenta(CreacionVentaDTO creacionVentaDTO) throws BusinessException {

        // Existencia de productos en la venta
        if(creacionVentaDTO.getDetallesVenta().isEmpty())
            throw new BadRequestException(VENTA_SIN_PRODUCTOS);

        // Obtención y validación de entidades necesarias para la venta
        Cliente cliente = clientePorIdUseCase.obtener(creacionVentaDTO.getIdCliente());
        Trabajador trabajador = trabajadorPorIdUseCase.obtener(creacionVentaDTO.getIdTrabajador());
        FormaPago formaPago = formaPagoPorIdUseCase.obtener(creacionVentaDTO.getIdFormaPago());
        Modalidad modalidad = modalidadPorIdUseCase.obtener(creacionVentaDTO.getIdModalidad());
        EstadoVenta estadoVentaActiva = estadoVentaActivaUseCase.obtener();
        EstadoVenta estadoVentaCancelada = estadoVentaCanceladaUseCase.obtener();

        // Se valida que la venta tenga coherencia con modalidad y forma de pago
        if(modalidad.getNombre().equals(MODALIDAD_CREDITO) && formaPago.getNombre().equals(PAGO_CONTADO)){
            throw new BadRequestException(VENTA_CREDITO_CON_FORMA_PAGO_INCORRECTA);
        }

        if(modalidad.getNombre().equals(MODALIDAD_CONTADO) && !formaPago.getNombre().equals(PAGO_CONTADO)){
            throw new BadRequestException(VENTA_CONTADO_CON_FORMA_PAGO_INCORRECTA);
        }

        // Generacion de detalles de venta
        List<DetalleVenta> detallesVenta = detallesVentaSinVentaAsociadaUseCase.generar(creacionVentaDTO.getDetallesVenta(), creacionVentaDTO.getIdModalidad());

        // calculo de valor total de la venta y actualizacion del cupo
        BigDecimal valorTotalCompra = calcularValorTotal(detallesVenta);

        if(modalidad.getNombre().equals(MODALIDAD_CREDITO))
            validarActualizarCupoUseCase.validarActualizar(cliente.getCuentaCliente(), valorTotalCompra, creacionVentaDTO.getCuotaInicial());

        // Se crea la venta y se guarda
        Venta venta = Venta.nuevo(
                OffsetDateTime.now(),
                valorTotalCompra,
                trabajador,
                formaPago,
                modalidad,
                cliente.getCuentaCliente(),
                modalidad.getNombre().equals(MODALIDAD_CREDITO) ? estadoVentaActiva : estadoVentaCancelada
        );
        venta = ventaRepository.save(venta);

        // Se enlazan los detalles y se guardan
        enlazarDetallesAVentaYGuardarUseCase.enlazarGuardar(detallesVenta, venta);

        if(modalidad.getNombre().equals(MODALIDAD_CREDITO)){
            // Creación plan de cuotas
            planCuotasUseCase.generar(valorTotalCompra, creacionVentaDTO.getCuotaInicial(), formaPago.getNumeroDias(), formaPago.getValorMinimo(), venta);
        }

        if(modalidad.getNombre().equals(MODALIDAD_CONTADO)){
            // Creación cuota del total
            cuotaPagoContadoUseCase.generar(valorTotalCompra, venta, trabajador);
        }

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
