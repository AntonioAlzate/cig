package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.dimension.Dimension;

public class DimensionesHelper {
    public static Dimension crearDiemension() throws BusinessException {
        return Dimension.construir(
          GeneralHelper.obtenerEnteroAleatorio(),
          GeneralHelper.obtenerValorBigDecimalAleatorio(),
          GeneralHelper.obtenerValorBigDecimalAleatorio()
        );
    }
}
