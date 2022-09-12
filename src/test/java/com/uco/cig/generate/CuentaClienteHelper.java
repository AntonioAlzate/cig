package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuentacliente.CuentaCliente;

import java.math.BigDecimal;

public class CuentaClienteHelper {
    public static CuentaCliente crearCuentaCliente() throws BusinessException {
        return CuentaCliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                EstadoCuentaClienteHelper.crearEstadoCuentaCliente(),
                DetalleCuentaFavorHelper.crearDetalleCuentaFavor()
        );
    }

    public static CuentaCliente crearCuentaClienteSinCupo() throws BusinessException {
        return CuentaCliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                BigDecimal.ZERO,
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                EstadoCuentaClienteHelper.crearEstadoCuentaCliente(),
                DetalleCuentaFavorHelper.crearDetalleCuentaFavor()
        );
    }

    public static CuentaCliente crearCuentaClienteVeinteCupo() throws BusinessException {
        return CuentaCliente.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                BigDecimal.valueOf(20L),
                GeneralHelper.obtenerValorBigDecimalAleatorio(),
                EstadoCuentaClienteHelper.crearEstadoCuentaCliente(),
                DetalleCuentaFavorHelper.crearDetalleCuentaFavor()
        );
    }
}
