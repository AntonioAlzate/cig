package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.cuentacliente.CuentaCliente;

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
}
