package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuota.Cuota;
import com.uco.cig.domain.estado.cuota.EstadoCuota;
import com.uco.cig.domain.trabajador.Trabajador;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public class CuotasHelper {

    public static Cuota crearCuota() throws BusinessException {
        return Cuota.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                LocalDate.now(),
                OffsetDateTime.now(),
                VentaHelper.crearVenta(),
                TrabajadorHelper.crearTrabajador(),
                TipoCobroHelper.crearTipoCobro(),
                EstadoCuotaHelper.crearEstadoCuota()
        );
    }

    public static Cuota crearCuota(Trabajador trabajador, EstadoCuota estadoCuota) throws BusinessException {
        return Cuota.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                LocalDate.now(),
                OffsetDateTime.now(),
                VentaHelper.crearVenta(),
                trabajador,
                TipoCobroHelper.crearTipoCobro(),
                estadoCuota
        );
    }
}
