package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.pais.Pais;

import java.util.UUID;

public class PaisHelper {
    public static Pais crearPais() throws BusinessException {
        return Pais.construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }

}
