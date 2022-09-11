package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.detalle.cuentafavor.DetalleCuentaFavor;

public class DetalleCuentaFavorHelper {
    public static DetalleCuentaFavor crearDetalleCuentaFavor() throws BusinessException {
        return DetalleCuentaFavor.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio()
        );
    }
}
