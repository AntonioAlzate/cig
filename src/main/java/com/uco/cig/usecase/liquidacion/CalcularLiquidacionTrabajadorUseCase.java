package com.uco.cig.usecase.liquidacion;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.estado.liquidacion.EstadoLiquidacion;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.liquidacion.ports.LiquidacionRepository;
import com.uco.cig.domain.tipocobro.TipoCobro;
import com.uco.cig.domain.trabajador.Trabajador;
import com.uco.cig.domain.venta.Venta;
import com.uco.cig.shared.dtos.DatosLiquidacionDTO;
import com.uco.cig.usecase.cuota.ListarCuotasCobradasTrabajadorUseCase;
import com.uco.cig.usecase.trabajador.ObtenerTrabajadorPorIdUseCase;
import com.uco.cig.usecase.venta.ListarVentasTrabajadorUseCase;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

@Service
@Transactional
public class CalcularLiquidacionTrabajadorUseCase {

    private final LiquidacionRepository liquidacionRepository;
    private final ListarCuotasCobradasTrabajadorUseCase cuotasCobradasTrabajadorUseCase;
    private final ListarVentasTrabajadorUseCase ventasTrabajadorUseCase;
    private final ObtenerTrabajadorPorIdUseCase obtenerTrabajadorPorIdUseCase;

    public CalcularLiquidacionTrabajadorUseCase(LiquidacionRepository liquidacionRepository, ListarCuotasCobradasTrabajadorUseCase cuotasCobradasTrabajadorUseCase, ListarVentasTrabajadorUseCase ventasTrabajadorUseCase, ObtenerTrabajadorPorIdUseCase obtenerTrabajadorPorIdUseCase) {
        this.liquidacionRepository = liquidacionRepository;
        this.cuotasCobradasTrabajadorUseCase = cuotasCobradasTrabajadorUseCase;
        this.ventasTrabajadorUseCase = ventasTrabajadorUseCase;
        this.obtenerTrabajadorPorIdUseCase = obtenerTrabajadorPorIdUseCase;
    }

    public DatosLiquidacionDTO calcular(Integer idTrabajador, OffsetDateTime fechaRealizacion, Boolean crearLiquidacion) throws BusinessException {
        //todo:
        TipoCobro tipoCobroInicial = TipoCobro.Construir(1, "INICIAL");
        TipoCobro tipoCobroNormal = TipoCobro.Construir(2, "NORMAL");
        EstadoLiquidacion estadoLiquidacion = new EstadoLiquidacion(2, "CANCELADA");

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

        if(crearLiquidacion && total.compareTo(BigDecimal.ZERO) != 0) {

            Trabajador trabajador = obtenerTrabajadorPorIdUseCase.obtener(idTrabajador);

            Liquidacion liquidacion = Liquidacion.nuevo(
                    OffsetDateTime.now(),
                    total,
                    trabajador,
                    estadoLiquidacion
            );

            liquidacionRepository.save(liquidacion);
        }

        return datosLiquidacionDTO;
    }
}
