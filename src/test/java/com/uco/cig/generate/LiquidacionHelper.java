package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.liquidacion.Liquidacion;
import com.uco.cig.domain.trabajador.Trabajador;

import java.time.OffsetDateTime;

public class LiquidacionHelper {
    public static Liquidacion crearLiquidacion() throws BusinessException {
        return Liquidacion.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                OffsetDateTime.now(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                TrabajadorHelper.crearTrabajador(),
                EstadoLiquidacionHelper.crearEstadoLiquidacion()
        );
    }

    public static Liquidacion crearLiquidacion(Trabajador trabajador) throws BusinessException {
        return Liquidacion.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                OffsetDateTime.now(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                trabajador,
                EstadoLiquidacionHelper.crearEstadoLiquidacion()
        );
    }
}
