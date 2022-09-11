package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.tipocobro.TipoCobro;

import java.util.UUID;

public class TipoCobroHelper {
    public static TipoCobro crearTipoCobro() throws BusinessException {
        return TipoCobro.Construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
