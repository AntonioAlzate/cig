package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.modalidad.Modalidad;

import java.util.UUID;

public class ModalidadHelper {

    public static Modalidad crearModalidad() throws BusinessException {
        return Modalidad.Construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString()
        );
    }
}
