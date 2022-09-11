package com.uco.cig.generate;

import com.uco.cig.domain.businessexception.BusinessException;
import com.uco.cig.domain.formapago.FormaPago;

import java.util.UUID;

public class FormaPagoHelper {

    public static FormaPago crearFormaPago() throws BusinessException {
        return FormaPago.Construir(
                GeneralHelper.obtenerEnteroAleatorio(),
                UUID.randomUUID().toString(),
                GeneralHelper.obtenerEnteroAleatorio(),
                GeneralHelper.obtenerValorBigDecimalAleatorio()
        );
    }
}
