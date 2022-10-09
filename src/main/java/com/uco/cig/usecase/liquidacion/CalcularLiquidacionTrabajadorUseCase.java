package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.businessexception.general.BadRequestException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.shared.dtos.DatosLiquidacionDTO;
import com.uco.cig.usecase.cuota.ListarCuotasCobradasTrabajadorUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroInicialUseCase;
import com.uco.cig.usecase.cuota.tipocobro.ConsultarTipoCobroNormalUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import com.uco.cig.usecase.venta.ListarVentasTrabajadorUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class CalcularLiquidacionTrabajadorUseCase {

    private static final String LIQUIDACION_YA_REGISTRADA = "Ya existe una liquidación registrada para la fecha especificada";

    private final LiquidacionRepository liquidacionRepository;
    private final ListarCuotasCobradasTrabajadorUseCase cuotasCobradasTrabajadorUseCase;
    private final ListarVentasTrabajadorUseCase ventasTrabajadorUseCase;
    private final ObtenerTrabajadorPorIdUseCase obtenerTrabajadorPorIdUseCase;
    private final ConsultarTipoCobroInicialUseCase consultarTipoCobroInicialUseCase;
    private final ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase;
    private final ConsultarEstadoLiquidacionCanceladaUseCase consultarEstadoLiquidacionCanceladaUseCase;

    public CalcularLiquidacionTrabajadorUseCase(LiquidacionRepository liquidacionRepository, ListarCuotasCobradasTrabajadorUseCase cuotasCobradasTrabajadorUseCase, ListarVentasTrabajadorUseCase ventasTrabajadorUseCase, ObtenerTrabajadorPorIdUseCase obtenerTrabajadorPorIdUseCase, ConsultarTipoCobroInicialUseCase consultarTipoCobroInicialUseCase, ConsultarTipoCobroNormalUseCase consultarTipoCobroNormalUseCase, ConsultarEstadoLiquidacionCanceladaUseCase consultarEstadoLiquidacionCanceladaUseCase) {
        this.liquidacionRepository = liquidacionRepository;
        this.cuotasCobradasTrabajadorUseCase = cuotasCobradasTrabajadorUseCase;
        this.ventasTrabajadorUseCase = ventasTrabajadorUseCase;
        this.obtenerTrabajadorPorIdUseCase = obtenerTrabajadorPorIdUseCase;
        this.consultarTipoCobroInicialUseCase = consultarTipoCobroInicialUseCase;
        this.consultarTipoCobroNormalUseCase = consultarTipoCobroNormalUseCase;
        this.consultarEstadoLiquidacionCanceladaUseCase = consultarEstadoLiquidacionCanceladaUseCase;
    }

    public DatosLiquidacionDTO calcular(Integer idTrabajador, OffsetDateTime fechaRealizacion, Boolean crearLiquidacion) throws BusinessException {
        TipoCobro tipoCobroInicial = consultarTipoCobroInicialUseCase.consultar();
        TipoCobro tipoCobroNormal = consultarTipoCobroNormalUseCase.consultar();
        EstadoLiquidacion estadoLiquidacion = consultarEstadoLiquidacionCanceladaUseCase.consultar();

        DatosLiquidacionDTO datosLiquidacionDTO = new DatosLiquidacionDTO();

        List<Cuota> cuotas = cuotasCobradasTrabajadorUseCase.obtener(idTrabajador, fechaRealizacion);

        BigDecimal valorCuotasIniciales = BigDecimal.ZERO;
        BigDecimal valorCuotasNormales = BigDecimal.ZERO;
        for (Cuota cuota : cuotas) {
            valorCuotasIniciales = cuota.getTipoCobro().getId().equals(tipoCobroInicial.getId()) ? valorCuotasIniciales.add(cuota.getValorCobro()) : valorCuotasIniciales;
            valorCuotasNormales = cuota.getTipoCobro().getId().equals(tipoCobroNormal.getId()) ? valorCuotasNormales.add(cuota.getValorCobro()) : valorCuotasNormales;
        }

        BigDecimal valorVentas = BigDecimal.ZERO;
        List<Venta> ventas = ventasTrabajadorUseCase.obtener(idTrabajador, fechaRealizacion);
        for (Venta venta : ventas) {
            valorVentas = valorVentas.add(venta.getValorTotal());
        }

        datosLiquidacionDTO.setTotalCobrosNormales(valorCuotasNormales);
        datosLiquidacionDTO.setTotalCobrosIniciales(valorCuotasIniciales);
        datosLiquidacionDTO.setTotalVentas(valorVentas);

        BigDecimal total = datosLiquidacionDTO.getTotalCobrosIniciales()
                .add(datosLiquidacionDTO.getTotalCobrosNormales())
                .add(datosLiquidacionDTO.getTotalVentas());

        if (Boolean.TRUE.equals(crearLiquidacion) && total.compareTo(BigDecimal.ZERO) != 0) {

            Trabajador trabajador = obtenerTrabajadorPorIdUseCase.obtener(idTrabajador);

            List<Liquidacion> liquidacionesTrabajadores = liquidacionRepository.findAllByTrabajador(trabajador);

            Boolean existe = existeLiquidacionParaFecha(liquidacionesTrabajadores, fechaRealizacion);

            if(Boolean.TRUE.equals(existe))
                throw new BadRequestException(LIQUIDACION_YA_REGISTRADA);

            Liquidacion liquidacion = Liquidacion.nuevo(
                    fechaRealizacion,
                    total,
                    trabajador,
                    estadoLiquidacion
            );

            datosLiquidacionDTO.setLiquidacion(liquidacionRepository.save(liquidacion));
        }

        return datosLiquidacionDTO;
    }

    private Boolean existeLiquidacionParaFecha(List<Liquidacion> liquidacionesTrabajadores, OffsetDateTime fechaRealizacion) {
        if (liquidacionesTrabajadores.isEmpty())
            return false;

        int diaRealizacion = fechaRealizacion.getDayOfYear();
        int anioRealizacion = fechaRealizacion.getYear();

        for (Liquidacion liquidacion : liquidacionesTrabajadores) {
            if (liquidacion.getFecha().getDayOfYear() == diaRealizacion && liquidacion.getFecha().getYear() == anioRealizacion) {
                return true;
            }
        }

        return false;
    }
}
